require 'rspec'
require_relative '../src/prototyped_object'
require_relative '../src/prototyped_constructor'

describe 'ProtorypedObject Parte 3' do

  it 'creando un guerrero ' do
    guerrero_proto = PrototypedObject.new
    guerrero_proto.energia = 100
    expect(guerrero_proto.energia).to eq(100)
    guerrero_proto.potencial_defensivo = 10
    guerrero_proto.potencial_ofensivo = 30
    expect(guerrero_proto.potencial_defensivo).to eq(10)
    expect(guerrero_proto.potencial_ofensivo).to eq(30)

    guerrero_proto.atacar_a =  proc {
        |otro_guerrero|
      if( otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
        otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
      end
    }

    guerrero_proto.recibe_danio = proc{
        |una_energia|
      self.energia = [self.energia - una_energia, 0].max
    }
    Guerrero = PrototypedConstructor.copy(guerrero_proto)
    un_guerrero = Guerrero.new
    expect(un_guerrero.energia).to eq(100)
    Guerrero.new.atacar_a(un_guerrero)
    expect(un_guerrero.energia).to eq(80)

  end

  it 'creando un guerrero a partir del Initialize ' do
    guerrero_proto = PrototypedObject.new proc{
      self.energia = 100
      self.potencial_defensivo = 10
      self.potencial_ofensivo = 30

      self.atacar_a = proc{
          |otro_guerrero|
        if( otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
          otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
        end
      }
      self.recibe_danio = proc{
          |una_energia|
        self.energia = [self.energia - una_energia, 0].max
      }
    }
    expect(guerrero_proto.energia).to eq(100)
    expect(guerrero_proto.potencial_defensivo).to eq(10)
    expect(guerrero_proto.potencial_ofensivo).to eq(30)

  end

  it 'Creando un guerrero a partir del metodo create and with'do
    Guerrero  = PrototypedConstructor.create {
      self.atacar_a = proc{
          |otro_guerrero|
        if( otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
          otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
        end
      }
      self.recibe_danio = proc{
          |una_energia|
        self.energia = [self.energia - una_energia, 0].max
      }
    }.with_properties([:energia, :potencial_ofensivo, :potencial_defensivo])

    expect(Guerrero.prototyped.energia).to eq(nil)

    atila = Guerrero.new({energia:100,potencial_ofensivo:50,potencial_defensivo:10 })
    expect(atila.energia).to eq(100)
    expect(atila.potencial_ofensivo).to eq(50)
    expect(atila.potencial_defensivo).to eq(10)

    proto_atila = atila.prototype
    proto_atila.potencial_ofensivo = 1000

    expect(atila.potencial_ofensivo).to eq(50)

  end

  it 'Creando un guerrero extendido' do
    Guerrero  = PrototypedConstructor.create {
      self.atacar_a = proc{
          |otro_guerrero|
        if( otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
          otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
        end
      }
      self.recibe_danio = proc{
          |una_energia|
        self.energia = [self.energia - una_energia, 0].max
      }
    }.with_properties([:energia, :potencial_ofensivo, :potencial_defensivo])

    Espadachin = Guerrero.extended {
        |  una_habliidad, una_potencia_espada|
      self.habilidad = una_habliidad
      self.potencia_espada = una_potencia_espada

      self.potencial_ofensivo =
          @potencial_ofensivo + self.potencia_espada * self.habilidad

    }

    un_espadachin = Espadachin.new({energia: 100, potencial_defensivo: 30, potencial_ofensivo: 10}, 0.1, 30)
    expect(un_espadachin.energia).to eq(100)
    expect(un_espadachin.habilidad).to eq(0.1)
    expect(un_espadachin.potencia_espada).to eq(30)
    expect(un_espadachin.potencial_ofensivo).to eq(13)
  end

end