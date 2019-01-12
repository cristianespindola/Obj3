require_relative '../src/prototyped'



class CervezaMagica

  include Prototyped

  attr_accessor  :listaDeBloquesYSelector

  def initialize()

    @listaDeBloquesYSelector = Array.new

  end

end