module type S = sig
  type key
  type 'a t
  val empty        : 'a t
  val empty_monoid : 'a t
  val is_empty     : 'a t -> bool
  val singleton    : key -> 'a -> 'a t
  val add          : key -> 'a -> 'a t -> 'a t
  val remove       : key -> 'a t -> 'a t
  val find_opt     : key -> 'a t -> 'a option
  val mem          : key -> 'a t -> bool
  val map          : ('a -> 'b) -> 'a t -> 'b t
  val filter       : (key -> 'a -> bool) -> 'a t -> 'a t
  val fold_left    : ('b -> key -> 'a -> 'b) -> 'b -> 'a t -> 'b
  val fold_right   : (key -> 'a -> 'b -> 'b) -> 'a t -> 'b -> 'b
  val append       : ('a -> 'a -> 'a) -> 'a t -> 'a t -> 'a t
  val compare      : ('a -> 'a -> int)  -> 'a t -> 'a t -> int
  val equal        : ('a -> 'a -> bool) -> 'a t -> 'a t -> bool
  val iter_inorder : (key -> 'a -> unit) -> 'a t -> unit
end

module Make (Ord : Ordered.ORDERED) : S with type key = Ord.t = struct
  type key = Ord.t

  type color = R | B
  type 'a t =
    | E
    | T of color * 'a t * key * 'a * 'a t

  let empty : 'a t = E
  let empty_monoid = empty
  let is_empty = function E -> true | _ -> false
  let singleton k v = T (B, E, k, v, E)

  (* вставка с балансировкой *)

  let balance = function
    | B, T (R, T (R, a, xk, xv, b), yk, yv, c), zk, zv, d
    | B, T (R, a, xk, xv, T (R, b, yk, yv, c)), zk, zv, d
    | B, a, xk, xv, T (R, T (R, b, yk, yv, c), zk, zv, d)
    | B, a, xk, xv, T (R, b, yk, yv, T (R, c, zk, zv, d)) ->
        T (R, T (B, a, xk, xv, b), yk, yv, T (B, c, zk, zv, d))
    | col, l, k, v, r -> T (col, l, k, v, r)

  let add k v t =
    let rec ins = function
      | E -> T (R, E, k, v, E)
      | T (col, l, kk, vv, r) ->
          let c = Ord.compare k kk in
          if c < 0 then balance (col, ins l, kk, vv, r)
          else if c > 0 then balance (col, l, kk, vv, ins r)
          else T (col, l, k, v, r)  
    in
    match ins t with
    | E -> assert false
    | T (_, l, kk, vv, r) -> T (B, l, kk, vv, r) 

  let rec find_opt k = function
    | E -> None
    | T (_, l, kk, v, r) ->
        let c = Ord.compare k kk in
        if c = 0 then Some v
        else if c < 0 then find_opt k l
        else find_opt k r

  let mem k t = Option.is_some (find_opt k t)

  (* ленивый итератор *)
  type 'a cursor = ('a t) list

  let rec push_left t st =
    match t with
    | E -> st
    | T (_, l, _, _, _) as n -> push_left l (n :: st)

  let cursor_start t = push_left t []

  let rec cursor_next (st : 'a cursor) : ((key * 'a) * 'a cursor) option =
    match st with
    | [] -> None
    | E :: rest -> cursor_next rest
    | T (_, _l, k, v, r) :: rest ->
        let st' = push_left r rest in
        Some ((k, v), st')

  let iter_inorder f t =
    let rec loop st =
      match cursor_next st with
      | None -> ()
      | Some ((k, v), st') -> f k v; loop st'
    in
    loop (cursor_start t)

  let fold_left f acc t =
    let rec loop acc st =
      match cursor_next st with
      | None -> acc
      | Some ((k, v), st') -> loop (f acc k v) st'
    in
    loop acc (cursor_start t)

  let rec fold_right f t acc =
    match t with
    | E -> acc
    | T (_, l, k, v, r) ->
        let acc1 = fold_right f r acc in
        let acc2 = f k v acc1 in
        fold_right f l acc2

  let rec map g = function
    | E -> E
    | T (c, l, k, v, r) -> T (c, map g l, k, g v, map g r)

  let filter p t =
    fold_left
      (fun acc k v -> if p k v then add k v acc else acc)
      empty t

  (* ---------- моноид ---------- *)
  let append combine a b =
    fold_left
      (fun acc k vb ->
         match find_opt k acc with
         | None    -> add k vb acc
         | Some va -> add k (combine va vb) acc)
      a b


  let compare cmpv a b =
    let rec loop ia ib =
      match cursor_next ia, cursor_next ib with
      | None, None -> 0
      | None, Some _ -> -1
      | Some _, None -> 1
      | Some ((ka, va), ia'), Some ((kb, vb), ib') ->
          let ck = Ord.compare ka kb in
          if ck <> 0 then ck
          else
            let cv = cmpv va vb in
            if cv <> 0 then cv else loop ia' ib'
    in
    loop (cursor_start a) (cursor_start b)

  let equal eqv a b =
    let rec loop ia ib =
      match cursor_next ia, cursor_next ib with
      | None, None -> true
      | None, Some _ | Some _, None -> false
      | Some ((ka, va), ia'), Some ((kb, vb), ib') ->
          Ord.compare ka kb = 0 && eqv va vb && loop ia' ib'
    in
    loop (cursor_start a) (cursor_start b)


  let remove k t =
    fold_left
      (fun acc kk vv -> if Ord.compare k kk = 0 then acc else add kk vv acc)
      empty t
end
