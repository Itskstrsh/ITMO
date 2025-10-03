module type ORDERED = sig
  type t

  val compare : t -> t -> int
end
