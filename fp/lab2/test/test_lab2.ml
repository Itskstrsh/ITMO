open Alcotest

module IntOrd = struct
  type t = int
  let compare = Int.compare
end

module D = Lab2.Dict.Make(IntOrd)

let of_list xs =
  List.fold_left (fun acc (k,v) -> D.add k v acc) D.empty xs

(* unit tests *)
let test_basic_ops () =
  let d = of_list [1,"a"; 2,"b"; 3,"c"] in
  Alcotest.(check bool) "mem 2" true (D.mem 2 d);
  Alcotest.(check (option string)) "find 1" (Some "a") (D.find_opt 1 d);

  let d' = D.remove 2 d in
  Alcotest.(check bool) "mem 2 after remove" false (D.mem 2 d');

  let d2 = D.map String.uppercase_ascii d in
  Alcotest.(check (option string)) "map keeps value"
    (Some "B") (D.find_opt 2 d2);

  let d3 = D.filter (fun k _ -> k mod 2 = 1) d in
  Alcotest.(check bool) "filter drops even keys" false (D.mem 2 d3);

  let keys_l = D.fold_left (fun acc k _ -> k :: acc) [] d |> List.rev in
  let keys_r = D.fold_right (fun k _ acc -> k :: acc) d [] in
  Alcotest.(check (list int)) "fold orders equal" keys_l keys_r

(* property-based tests *)
module QC = QCheck

let gen_dict =
  let open QC in
  let gen_pair = Gen.(map2 (fun k v -> (k,v)) small_int small_int) in
  let gen_list = Gen.list_size (Gen.int_range 0 60) gen_pair in
  make ~print:(Print.list (Print.pair Print.int Print.int)) gen_list
  |> set_shrink Shrink.list
  |> map of_list

(* 1) mem <-> find_opt *)
let prop_mem_find =
  QC.Test.make ~name:"mem <-> find_opt"
    QC.(pair small_int gen_dict)
    (fun (k, d) ->
       D.mem k d = Option.is_some (D.find_opt k d))

(* 2) моноид: нейтральный элемент и ассоциативность *)
let combine_left x _ = x

let prop_monoid_identity =
  QC.Test.make ~name:"monoid identity" gen_dict (fun d ->
    D.equal (=)
      (D.append combine_left d D.empty_monoid)
      d
    &&
    D.equal (=)
      (D.append combine_left D.empty_monoid d)
      d
  )

let prop_monoid_assoc =
  QC.Test.make ~name:"monoid associativity"
    QC.(triple gen_dict gen_dict gen_dict)
    (fun (a,b,c) ->
      let l = D.append combine_left (D.append combine_left a b) c in
      let r = D.append combine_left a (D.append combine_left b c) in
      D.equal (=) l r)

(* 3) equal <-> compare *)
let prop_equal_compare =
  QC.Test.make ~name:"equal <-> compare"
    QC.(pair gen_dict gen_dict)
    (fun (a,b) ->
      if D.equal (=) a b then D.compare Int.compare a b = 0
      else D.compare Int.compare a b <> 0)

let () =
  run "lab2"
    [ "unit",  [ test_case "basic" `Quick test_basic_ops ];
      "props", [
        QCheck_alcotest.to_alcotest prop_mem_find;
        QCheck_alcotest.to_alcotest prop_monoid_identity;
        QCheck_alcotest.to_alcotest prop_monoid_assoc;
        QCheck_alcotest.to_alcotest prop_equal_compare;
      ];
    ]
