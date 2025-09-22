# Лабораторная №1 Project Euler #8 и #22

## Описание проблемы

**Задача A (Euler #8):** дано 1000-значное число. Требуется найти максимальное произведение `k = 13` подряд идущих цифр.

**Задача B (Euler #22):** дан CSV-файл `names.txt` с именами в кавычках. Нужно:
1) отсортировать имена по алфавиту;
2) посчитать «оценку» имени (`A=1..Z=26`);
3) умножить её на позицию в отсортированном списке;
4) сложить по всем именам.

Обе задачи реализованы несколькими стилями: хвостовая/обычная рекурсия, модульный стиль `map/fold`, через индексы, императивные циклы и на `Seq`.

---

## Ключевые элементы реализации (минимальные комментарии)

### Euler #8 — максимум произведения 13 подряд идущих цифр

Парсинг в список/массив цифр:

    let digits_of_string s =
      String.to_seq s
      |> Seq.filter (fun c -> '0' <= c && c <= '9')
      |> Seq.map (fun c -> Char.code c - Char.code '0')
      |> List.of_seq

    let digits_list = digits_of_string big_number_str
    let digits_arr  = Array.of_list digits_list

Произведение окна (в `int64`):

    let product_slice64 (arr:int array) (i:int) (k:int) : int64 =
      let rec loop j left acc =
        if left = 0 then acc
        else loop (j+1) (left-1) Int64.(acc * of_int arr.(j))
      in
      loop i k 1L

Варианты решения (фрагменты):

    (* 1) хвостовая рекурсия по началу окна *)
    let max_product_tail k arr =
      let n = Array.length arr in
      let rec scan i best =
        if i + k > n then best
        else scan (i+1) Int64.(max best (product_slice64 arr i k))
      in scan 0 0L

    (* 2) обычная рекурсия *)
    let rec max_product_non_tail k arr i =
      let n = Array.length arr in
      if i + k > n then 0L
      else Int64.max (product_slice64 arr i k) (max_product_non_tail k arr (i+1))

    (* 3) модульный стиль: окна -> map -> fold *)
    let max_via_map k arr =
      let n = Array.length arr in
      List.init (n - k + 1) (fun i -> product_slice64 arr i k)
      |> List.fold_left Int64.max 0L

    (* 4) императивные for-циклы; 5) Seq — аналогично *)

---

### Euler #22 — сумма «оценок» имён

Простой CSV-парсер (один проход, снятие кавычек):

    let split_csv_names s =
      let n = String.length s and buf = Buffer.create 16 in
      let rec loop i acc =
        if i = n then
          let t = Buffer.contents buf |> String.trim in
          let t =
            if String.length t >= 2 && t.[0] = '"' && t.[String.length t-1] = '"'
            then String.sub t 1 (String.length t-2) else t
          in
          List.rev (if t = "" then acc else t :: acc)
        else match s.[i] with
          | ',' ->
              let t = Buffer.contents buf |> String.trim in
              Buffer.clear buf;
              let t =
                if String.length t >= 2 && t.[0] = '"' && t.[String.length t-1] = '"'
                then String.sub t 1 (String.length t-2) else t
              in
              loop (i+1) (if t = "" then acc else t :: acc)
          | c -> Buffer.add_char buf c; loop (i+1) acc
      in loop 0 []

Оценка имени и суммирование:

    let name_value nm =
      String.fold_left (fun acc c ->
        let c = Char.uppercase_ascii c in
        if 'A' <= c && c <= 'Z'
        then acc + (Char.code c - Char.code 'A' + 1)
        else acc
      ) 0 nm

    let total_score_tail names_sorted =
      let rec loop idx acc = function
        | [] -> acc
        | nm :: tl ->
            loop (idx+1) Int64.(acc + of_int (idx * name_value nm)) tl
      in loop 1 0L

Другие варианты: обычная рекурсия, `List.mapi` + `fold_left`, индексы через `List.init`, императивные `for`, ленивые `Seq`.

---

## Выводы

- Использование **нескольких парадигм** на одной задаче повышает уверенность в корректности: функциональные (`map/fold/Seq`), рекурсивные и императивные решения сходятся к одному ответу.
- **Хвостовая рекурсия** даёт предсказуемую глубину стека; **обычная рекурсия** короче по коду, но требует аккуратности.
- **Императивные массивы и циклы** в OCaml удобны и производительны для плотных скользящих окон.
- **Ленивые `Seq`** упрощают генерацию окон/потоков без промежуточных структур; для фиксированного окна не сложнее for-подхода.
- Для произведений безопаснее сразу использовать **`int64`**, чтобы избежать переполнений.
- Для `names.txt` достаточно надёжного **посимвольного CSV-парсера** с `Buffer` и снятием внешних кавычек.
