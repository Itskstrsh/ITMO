module Euler22 = struct


let read_all (path : string) : string =
  let ch = open_in_bin path in
  let len = in_channel_length ch in
  let s = really_input_string ch len in
  close_in ch;
  s

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
  let score c =
    let c = Char.uppercase_ascii c in
    if 'A' <= c && c <= 'Z' then Char.code c - Char.code 'A' + 1 else 0
  in
  let rec loop i acc =
    if i = String.length name then acc
    else loop (i + 1) (acc + score name.[i])
  in
  loop 0 0


let sort_names (xs : string list) : string list = List.sort String.compare xs

(* 1. Монолитные реализации*)
(* 1a) Хвостовая рекурсия*)
let tail_score (names_sorted : string list) : int64 =
  let rec loop idx acc = function
    | [] -> acc
    | nm :: tl ->
        let score = idx * name_value nm in
        loop (idx + 1) (Int64.add acc (Int64.of_int score)) tl
  in
  loop 1 0L names_sorted

(*1b) Обычная рекурсия *)
let rec non_tail_score (idx : int) (names_sorted : string list) : int64 =
  match names_sorted with
  | [] -> 0L
  | nm :: tl ->
      let here = Int64.of_int (idx * name_value nm) in
      Int64.add here (non_tail_score (idx + 1) tl)

(* 2. Модульная реализация *)
module Index = struct
  let attach (names : string list) : (int * string) list =
    List.mapi (fun i nm -> (i + 1, nm)) names
end

module Score = struct
  let by_name (i, nm : int * string) : int =
    i * name_value nm
end

module Reduce64 = struct
  let sum_int_as_int64 (xs : int list) : int64 =
    List.fold_left (fun acc v -> Int64.add acc (Int64.of_int v)) 0L xs
end

let modular_score (names_sorted : string list) : int64 =
  names_sorted
  |> Index.attach
  |> List.map Score.by_name
  |> Reduce64.sum_int_as_int64



(* 3. Генерация индексов через map *)
let map_score (names_sorted : string list) : int64 =
  let n = List.length names_sorted in
  let idxs = List.init n (fun i -> i + 1) in
  let values = List.map name_value names_sorted in
  List.fold_left2
    (fun acc i v -> Int64.add acc (Int64.of_int (i * v)))
    0L idxs values

(* 4. Спецсинтаксис циклов *)
let loop_score (names_sorted : string list) : int64 =
  let arr = Array.of_list names_sorted in
  let n = Array.length arr in
  let total = ref 0L in
  for i = 0 to n - 1 do
    let v = name_value arr.(i) in
    total := Int64.add !total (Int64.of_int ((i + 1) * v))
  done;
  !total

(* 5. Ленивые последовательности*)
let seq_score (names_sorted : string list) : int64 =
  let seq =
    List.to_seq names_sorted |> Seq.mapi (fun i nm -> (i + 1) * name_value nm)
  in
  Seq.fold_left (fun acc v -> Int64.add acc (Int64.of_int v)) 0L seq


let solution (csv : string) : int64 * (string * int64) list =
  let names = split_csv_names csv |> sort_names in
  let variants =
    [
      ("1a", tail_score names);
      ("1b", non_tail_score 1 names);
      ("2", modular_score names);
      ("3", map_score names);
      ("4", loop_score names);
      ("5", seq_score names);
    ]
  in
  let first = snd (List.hd variants) in
  (first, variants)

let () =
  let path = "names.txt" in
  let csv = try read_all path with _ -> failwith "Fail not found" in

  let expected = Some 871198282L in

  let _answer, variants = solution csv in
  List.iter
    (fun (name, v) ->
      let mark =
        match expected with
        | None -> ""
        | Some e -> if v = e then " OK" else " Failed "
      in
      Printf.printf "%-2s -> %Ld%s\n" name v mark)
    variants;
  Option.iter (fun e -> Printf.printf "Expected = %Ld\n" e) expected
end

module _ = Euler22