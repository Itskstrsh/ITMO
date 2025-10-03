module Euler8 : sig
  val k : int

  val digits_list : int list

  val digits_arr : int array

  val max_tail : int -> int array -> int64

  val max_non_tail : int -> int array -> int -> int64

  val modular : int -> int list -> int64

  val max_map : int -> int array -> int64

  val max_loops : int -> int array -> int64

  val max_seq : int -> int array -> int64
end
