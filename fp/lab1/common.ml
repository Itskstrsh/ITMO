module Euler8 = struct
  let dano =
    {|
73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450
|}

  let str_to_mas (s : string) : int list =
    let n = String.length s in
    let rec loop i acc =
      if i = n then List.rev acc
      else
        match s.[i] with
        | '0' .. '9' as c ->
            let d = Char.code c - Char.code '0' in
            loop (i + 1) (d :: acc)
        | _ -> loop (i + 1) acc
    in
    loop 0 []

  let digits_list : int list = str_to_mas dano
  let digits_arr : int array = Array.of_list digits_list

  let window_multip (arr : int array) (i : int) (cnt : int) : int64 =
    let rec loop j left acc =
      if left = 0 then acc
      else
        let acc' = Int64.mul acc (Int64.of_int arr.(j)) in
        loop (j + 1) (left - 1) acc'
    in
    loop i cnt 1L

  (* 1a. хвостовая *)
  let max_tail (k : int) (arr : int array) : int64 =
    let n = Array.length arr in
    let rec scan i best =
      if i + k > n then best
      else
        let p = window_multip arr i k in
        let best' = if p > best then p else best in
        scan (i + 1) best'
    in
    scan 0 0L

  (* 1b. не хвостовая *)
  let rec max_non_tail (k : int) (arr : int array) (i : int) : int64 =
    let n = Array.length arr in
    if i + k > n then 0L
    else
      let p_here = window_multip arr i k in
      Int64.max p_here (max_non_tail k arr (i + 1))

  (* модульная *)
  let rec take m xs =
    if m <= 0 then []
    else match xs with [] -> [] | x :: tl -> x :: take (m - 1) tl

  let length xs =
    let rec go acc = function [] -> acc | _ :: tl -> go (acc + 1) tl in
    go 0 xs

  let windows_k_list k xs =
    let rec build acc ys =
      if length ys < k then List.rev acc
      else build (take k ys :: acc) (List.tl ys)
    in
    build [] xs

  let multiply64 (xs : int list) : int64 =
    List.fold_left (fun acc d -> Int64.mul acc (Int64.of_int d)) 1L xs

  let modular (k : int) (xs : int list) : int64 =
    xs |> windows_k_list k
    |> List.filter (fun w -> not (List.exists (( = ) 0) w))
    |> List.map multiply64
    |> List.fold_left Int64.max 0L

  (* через map индексов *)
  let max_map (k : int) (arr : int array) : int64 =
    let n = Array.length arr in
    List.init (n - k + 1) (fun i -> window_multip arr i k)
    |> List.fold_left Int64.max 0L

  (* циклы *)
  let max_loops (k : int) (arr : int array) : int64 =
    let n = Array.length arr in
    let best = ref 0L in
    for i = 0 to n - k do
      let p = ref 1L in
      for j = i to i + k - 1 do
        p := Int64.mul !p (Int64.of_int arr.(j))
      done;
      if !p > !best then best := !p
    done;
    !best

  (* ленивые Seq *)
  let seq_multip (k : int) (arr : int array) : int64 Seq.t =
    let n = Array.length arr in
    Seq.unfold
      (fun i ->
        if i + k > n then None else Some (window_multip arr i k, i + 1))
      0

  let max_seq (k : int) (arr : int array) : int64 =
    Seq.fold_left Int64.max 0L (seq_multip k arr)
end

module Euler22 = struct
  
  let remove_all_quotes (s : string) : string =
  let b = Buffer.create (String.length s) in
  String.iter (fun c -> if c <> '"' then Buffer.add_char b c) s;
  Buffer.contents b

  let split_csv_names (s : string) : string list =
    s
    |> remove_all_quotes
    |> String.split_on_char ','
    |> List.map String.trim
    |> List.filter (fun t -> t <> "")


  let name_value (name : string) : int =
    let total = ref 0 in
    for i = 0 to String.length name - 1 do
      let c = Char.uppercase_ascii name.[i] in
      if 'A' <= c && c <= 'Z' then
        total := !total + (Char.code c - Char.code 'A' + 1)
    done;
    !total

  let sort_names (xs : string list) : string list = List.sort String.compare xs

  let tail_score (names_sorted : string list) : int64 =
    let rec loop idx acc = function
      | [] -> acc
      | nm :: tl ->
          let score = idx * name_value nm in
          loop (idx + 1) (Int64.add acc (Int64.of_int score)) tl
    in
    loop 1 0L names_sorted

  let rec non_tail_score (idx : int) (names_sorted : string list) : int64
      =
    match names_sorted with
    | [] -> 0L
    | nm :: tl ->
        let here = Int64.of_int (idx * name_value nm) in
        Int64.add here (non_tail_score (idx + 1) tl)

  let modular_score (names_sorted : string list) : int64 =
    names_sorted
    |> List.mapi (fun i nm -> (i + 1) * name_value nm)
    |> List.fold_left (fun acc v -> Int64.add acc (Int64.of_int v)) 0L

  let map_score (names_sorted : string list) : int64 =
    let n = List.length names_sorted in
    let idxs = List.init n (fun i -> i + 1) in
    let values = List.map name_value names_sorted in
    List.fold_left2
      (fun acc i v -> Int64.add acc (Int64.of_int (i * v)))
      0L idxs values

  let loop_score (names_sorted : string list) : int64 =
    let arr = Array.of_list names_sorted in
    let n = Array.length arr in
    let total = ref 0L in
    for i = 0 to n - 1 do
      let v = name_value arr.(i) in
      total := Int64.add !total (Int64.of_int ((i + 1) * v))
    done;
    !total

  let seq_score (names_sorted : string list) : int64 =
    let seq =
      List.to_seq names_sorted |> Seq.mapi (fun i nm -> (i + 1) * name_value nm)
    in
    Seq.fold_left (fun acc v -> Int64.add acc (Int64.of_int v)) 0L seq
end
