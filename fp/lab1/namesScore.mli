module Euler22 : sig
  val split_csv_names : string -> string list

  val sort_names : string list -> string list

  val name_value : string -> int

  val tail_score : string list -> int64

  val non_tail_score : int -> string list -> int64

  val modular_score : string list -> int64

  val map_score : string list -> int64

  val loop_score : string list -> int64

  val seq_score : string list -> int64

  val solution : string -> int64 * (string * int64) list
end
