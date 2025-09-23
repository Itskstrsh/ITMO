open Alcotest

let test_euler8 () =
  let open Common.Euler8 in
  let k = 13 in
  let exp = 23514624000L in
  check int64 "tail" exp (max_tail k digits_arr);
  check int64 "non" exp (max_non_tail k digits_arr 0);
  check int64 "mod" exp (modular k digits_list);
  check int64 "map" exp (max_map k digits_arr);
  check int64 "loops" exp (max_loops k digits_arr);
  check int64 "seq" exp (max_seq k digits_arr)

let test_euler22 () =
  let open Common.Euler22 in
  let csv = In_channel.with_open_text "names.txt" In_channel.input_all in
  let names = csv |> split_csv_names |> sort_names in
  let exp = 871198282L in
  check int64 "tail" exp (tail_score names);
  check int64 "non" exp (non_tail_score 1 names);
  check int64 "mod" exp (modular_score names);
  check int64 "map" exp (map_score names);
  check int64 "loops" exp (loop_score names);
  check int64 "seq" exp (seq_score names)

let () =
  run "lab1"
    [
      ("euler8", [ test_case "expected" `Quick test_euler8 ]);
      ("euler22", [ test_case "expected" `Quick test_euler22 ]);
    ]
