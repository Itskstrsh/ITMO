let read_all (path : string) : string =
  let ch = open_in_bin path in
  let len = in_channel_length ch in
  let s = really_input_string ch len in
  close_in ch;
  s

(* Разделяем по запятым и снимаем кавычки.*)
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

(* Оценка имени: A=1,..,Z=26; игнорируем не-буквы *)
let name_value (name : string) : int =
  let total = ref 0 in
  for i = 0 to String.length name - 1 do
    let c = Char.uppercase_ascii name.[i] in
    if 'A' <= c && c <= 'Z' then
      total := !total + (Char.code c - Char.code 'A' + 1)
  done;
  !total

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
let modular_score (names_sorted : string list) : int64 =
  names_sorted
  |> List.mapi (fun i nm -> (i + 1) * name_value nm) (* map с индексом *)
  |> List.fold_left (fun acc v -> Int64.add acc (Int64.of_int v)) 0L

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

(* Python реализация
   with open('names.txt', 'r') as f:
       s = f.read()
   names = [t.strip('"') for t in s.split(',')]
   names.sort()
   ans = sum((i+1)*sum(ord(c)-64 for c in name if c.isalpha()) for i, name in enumerate(names))
   print(ans)
*)

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
