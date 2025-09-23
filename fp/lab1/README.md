# Лабораторная №1 Project Euler #8 и #22

## Рощин Константин Эдуардович

## Описание проблемы

**[Project Euler — Problem 8](https://projecteuler.net/problem=8) :** дано 1000-значное число. Требуется найти максимальное произведение `k = 13` подряд идущих цифр.


**[Project Euler — Problem 22](https://projecteuler.net/problem=22) :** дан CSV-файл `names.txt` с именами в кавычках. Нужно:
1) отсортировать имена по алфавиту;
2) посчитать «оценку» имени (`A=1..Z=26`);
3) умножить её на позицию в отсортированном списке;
4) сложить по всем именам.

Обе задачи реализованы несколькими стилями: хвостовая/обычная рекурсия, модульный стиль `map/fold`, через индексы, императивные циклы и ленивые последовательности.

---

## Ключевые элементы реализации

### Euler #8 — максимум произведения 13 подряд идущих цифр

Парсинг в список/массив цифр:

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

Произведение окна (в `int64`):

    let window_multip (arr:int array) (i:int) (k:int) : int64 =
      let rec loop j left acc =
        if left = 0 then acc
        else loop (j+1) (left-1) Int64.(acc * of_int arr.(j))
      in
      loop i k 1L

Варианты решения (фрагменты):

    (* 1) хвостовая рекурсия по началу окна *)
    let max_tail k arr =
      let n = Array.length arr in
      let rec scan i best =
        if i + k > n then best
        else scan (i+1) Int64.(max best (window_multip arr i k))
      in scan 0 0L

    (* 2) обычная рекурсия *)
    let rec max_non_tail k arr i =
      let n = Array.length arr in
      if i + k > n then 0L
      else Int64.max (window_multip arr i k) (max_non_tail k arr (i+1))

    (* 3) модульный стиль: окна -> map -> fold *)
    let max_map k arr =
      let n = Array.length arr in
      List.init (n - k + 1) (fun i -> window_multip arr i k)
      |> List.fold_left Int64.max 0L

    (* 4) циклы *)
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

    (* 5) ленивые последовательности *)
    let seq_multip (k : int) (arr : int array) : int64 Seq.t =
      let n = Array.length arr in
      Seq.unfold
        (fun i ->
          if i + k > n then None
          else
            let p = window_multip arr i k in
            Some (p, i + 1))
        0

---

### Euler #22 — сумма «оценок» имён

Простой CSV-парсер:

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


Оценка имени:

    let name_value nm =
      String.fold_left (fun acc c ->
        let c = Char.uppercase_ascii c in
        if 'A' <= c && c <= 'Z'
        then acc + (Char.code c - Char.code 'A' + 1)
        else acc
      ) 0 nm

Варианты решения:

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
      
---

## Выводы

- Хвостовая рекурсия ведёт себя как обычный цикл: работает быстро и не ест лишнюю память. Обычная рекурсия короче на вид, но на длинных входах может упереться в стек.
- Там, где нужно много раз подряд пройти по данным, обычные массивы и `for`-циклы получаются проще и часто быстрее.
- `Seq` удобен, когда данные можно «делать по ходу» и не хранить лишние промежуточные списки — код получается аккуратнее.
- Для произведений я использовал `int64`, чтобы не ловить переполнения на больших числах.


**Итог:** Я посмотрел на базовые приёмы и абстракции языка OCaml. Реализовал простые алгоритмы и струтуры данных и применил их на задачах проекта Эйлера. 
