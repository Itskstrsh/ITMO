open Alcotest

let test_euler8 () =
  let open Common.Euler8 in
  let k = 13 in
  let exp = 23514624000L in
  check int64 "tail" exp (max_product_tail k digits_arr);
  check int64 "non" exp (max_product_non_tail k digits_arr 0);
  check int64 "mod" exp (modular_solution k digits_list);
  check int64 "map" exp (max_via_map k digits_arr);
  check int64 "loops" exp (max_via_for_loops k digits_arr);
  check int64 "seq" exp (max_via_seq k digits_arr)

let test_euler22 () =
  let open Common.Euler22 in
  (* В тесте читаем names.txt рядом с исходниками.
     Dune подставит его как dep, поэтому путь «names.txt» доступен. *)
  let csv = In_channel.with_open_text "names.txt" In_channel.input_all in
  let names = csv |> split_csv_names |> sort_names in
  let exp = 871198282L in
  check int64 "tail" exp (total_score_tail names);
  check int64 "non" exp (total_score_non_tail 1 names);
  check int64 "mod" exp (total_score_modular names);
  check int64 "map" exp (total_score_via_map_indices names);
  check int64 "loops" exp (total_score_for names);
  check int64 "seq" exp (total_score_seq names)

let () =
  run "lab1"
    [
      ("euler8", [ test_case "expected" `Quick test_euler8 ]);
      ("euler22", [ test_case "expected" `Quick test_euler22 ]);
    ]
