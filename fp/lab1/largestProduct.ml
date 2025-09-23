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

let k = 13

(* str -> [int]*)
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

(* Умножение цифр списка — Int64, чтобы избежать переполнения.*)
let multiply64 (xs : int list) : int64 =
  List.fold_left (fun acc d -> Int64.mul acc (Int64.of_int d)) 1L xs

(* Берёт первые [m] элементов списка *)
let rec take m xs =
  if m <= 0 then []
  else match xs with [] -> [] | x :: tl -> x :: take (m - 1) tl

(* Длина списка, хвоствовая рекурсия.*)
let length xs =
  let rec go acc ys = match ys with [] -> acc | _ :: tl -> go (acc + 1) tl in
  go 0 xs

(* Список окон размера k для списка. Неэффективный, но наглядный.*)
let windows_k_list (k : int) (xs : int list) : int list list =
  let rec build acc ys =
    if length ys < k then List.rev acc
    else build (take k ys :: acc) (List.tl ys)
  in
  build [] xs

(* 1. Монолитные реализации*)
(* 1a) Хвостовая рекурсия*)
let window_multip (arr : int array) (i : int) (cnt : int) : int64 =
  let rec loop j left acc =
    if left = 0 then acc
    else
      let acc' = Int64.mul acc (Int64.of_int arr.(j)) in
      loop (j + 1) (left - 1) acc'
  in
  loop i cnt 1L (* хвостовая рекурсия *)

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

(* 1b) Обычная рекурсия*)
let rec max_non_tail (k : int) (arr : int array) (i : int) : int64 =
  let n = Array.length arr in
  if i + k > n then 0L
  else
    let p_here = window_multip arr i k in
    Int64.max p_here (max_non_tail k arr (i + 1))

(* 2. Модульная реализация*)
let modular (k : int) (xs : int list) : int64 =
  xs |> windows_k_list k
  |> List.filter (fun w -> not (List.exists (( = ) 0) w))
  |> List.map multiply64 (* отображение: окно -> произведение *)
  |> List.fold_left Int64.max 0L (* свёртка: максимум *)

(* 3. Генерация последовательности с помощью map*)
(* Генерируем список индексов [0, ...,  n-k] и map-ом считаем P(i) *)
let max_map (k : int) (arr : int array) : int64 =
  let n = Array.length arr in
  let idxs = List.init (n - k + 1) (fun i -> i) in
  idxs
  |> List.map (fun i -> window_multip arr i k)
  |> List.fold_left Int64.max 0L

(* 4. Специальный синтаксис для циклов*)
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

(* 5. Ленивые последовательности*)
let seq_multip (k : int) (arr : int array) : int64 Seq.t =
  let n = Array.length arr in
  Seq.unfold
    (fun i ->
      if i + k > n then None
      else
        let p = window_multip arr i k in
        Some (p, i + 1))
    0

let max_seq (k : int) (arr : int array) : int64 =
  Seq.fold_left Int64.max 0L (seq_multip k arr)

(* 6. Python реализация
   from math import prod
   s = """7316717653......52963450"""
   digits = [int(c) for c in s if c.isdigit()]
   K = 13
   ans = 0
   for i in range(len(digits)-K+1):
       p = prod(digits[i:i+K])
       if p > ans:
           ans = p
   print(ans)  # 23514624000
*)

let () =
  let expected = 23514624000L in
  let answers =
    [
      ("1a", max_tail k digits_arr);
      ("1b", max_non_tail k digits_arr 0);
      ("2", modular k digits_list);
      ("3", max_map k digits_arr);
      ("4", max_loops k digits_arr);
      ("5", max_seq k digits_arr);
    ]
  in
  List.iter
    (fun (name, v) ->
      Printf.printf "%-2s : %Ld%s\n" name v
        (if v = expected then " OK " else " Failed"))
    answers;
  Printf.printf "Expected = %Ld\n" expected
