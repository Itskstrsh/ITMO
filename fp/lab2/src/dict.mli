module type S = sig
  type key

  type 'a t

  val empty : 'a t

  val empty_monoid : 'a t

  val is_empty : 'a t -> bool

  val singleton : key -> 'a -> 'a t

  val add : key -> 'a -> 'a t -> 'a t

  val remove : key -> 'a t -> 'a t

  val find_opt : key -> 'a t -> 'a option

  val mem : key -> 'a t -> bool

  val map : ('a -> 'b) -> 'a t -> 'b t

  val filter : (key -> 'a -> bool) -> 'a t -> 'a t

  val fold_left : ('b -> key -> 'a -> 'b) -> 'b -> 'a t -> 'b

  val fold_right : (key -> 'a -> 'b -> 'b) -> 'a t -> 'b -> 'b

  val append : ('a -> 'a -> 'a) -> 'a t -> 'a t -> 'a t

  val compare : ('a -> 'a -> int) -> 'a t -> 'a t -> int

  val equal : ('a -> 'a -> bool) -> 'a t -> 'a t -> bool

  val iter_inorder : (key -> 'a -> unit) -> 'a t -> unit
end

module Make (Ord : Ordered.ORDERED) : S with type key = Ord.t
