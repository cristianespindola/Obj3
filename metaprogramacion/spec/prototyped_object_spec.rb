require 'rspec'
require_relative '../src/prototyped_object'
require_relative '../src/cerveza_magica'
describe 'Guerrero create' do

  it 'seteando propiedades a un PrototypedObject' do
    guerrero = PrototypedObject.new
    guerrero.set_property(:energia,100)
    expect(guerrero.energia).to eq(100)
    guerrero.set_property(:potencial_defensivo,10)
    expect(guerrero.potencial_defensivo).to eq(10)
    guerrero.set_property(:protencial_ofensivo,30)
    expect(guerrero.protencial_ofensivo).to eq(30)

    guerrero.energia = 150
    expect(guerrero.energia).to eq(150)
  end

  it 'agregando un metodo a un objeto Prototyped' do
    guerrero = PrototypedObject.new
    guerrero.set_method(:groso, proc{"Soy Re Groso"})
    expect(guerrero.groso).to eq("Soy Re Groso")
    expect(guerrero.listaDeBloquesYSelector.length).to eq(1)

  end

  it ' agregando metodos y propiedades a un PrototypeObject' do
    guerrero = PrototypedObject.new
    guerrero.set_property(:energia,100)
    expect(guerrero.energia).to eq(100)
    guerrero.set_property(:potencial_defensivo,10)
    expect(guerrero.potencial_defensivo).to eq(10)
    guerrero.set_property(:potencial_ofensivo,30)
    expect(guerrero.potencial_ofensivo).to eq(30)
    guerrero.set_method(:recibe_danio, proc{ |una_energia|
      self.energia = [self.energia - una_energia, 0].max

    })
    guerrero.set_method(:atacar_a ,proc{
        |otro_guerrero|
      if( otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
        otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
      end
    })
    otroG = guerrero.clone
    guerrero.atacar_a otroG
    expect(otroG.energia).to eq(80)

  end

  it ' agregando una prototipo a espadachin' do
    guerrero = PrototypedObject.new
    guerrero.set_property(:energia,100)
    expect(guerrero.energia).to eq(100)
    guerrero.set_property(:potencial_defensivo,10)
    expect(guerrero.potencial_defensivo).to eq(10)
    guerrero.set_property(:potencial_ofensivo,30)
    expect(guerrero.potencial_ofensivo).to eq(30)
    guerrero.set_method(:recibe_danio, proc{ |una_energia|
      self.energia = [self.energia - una_energia, 0].max

    })
    guerrero.set_method(:atacar_a ,proc{
        |otro_guerrero|
      if( otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
        otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
      end
    })

    espadachin = PrototypedObject.new
    espadachin.set_prototype(guerrero)
    espadachin.set_property(:habilidad, 0.5)
    espadachin.set_property(:potencial_espada, 30)
    espadachin.energia = 100
    espadachin.potencial_defensivo = 10
    espadachin.potencial_ofensivo = 30
    expect(espadachin.habilidad).to eq(0.5)
    expect(espadachin.potencial_espada).to eq(30)
    expect(espadachin.energia).to eq(100)
    expect(espadachin.potencial_defensivo).to eq(10)
    expect(espadachin.potencial_ofensivo).to eq(30)

    espadachin.set_method(:potencial_ofensivo, proc{
      @potencial_ofensivo + self.potencial_espada * self.habilidad

    })

    expect(espadachin.potencial_ofensivo).to eq(45)
    otroGuerrero = guerrero.clone
    expect(otroGuerrero.energia).to eq(100)
    espadachin.atacar_a(otroGuerrero)
    expect(otroGuerrero.energia).to eq(65)

    guerrero.set_method(:sanar, proc {
      self.energia = self.energia + 10
    })
    espadachin.sanar
    expect(espadachin.energia).to eq(110)
    expect{otroGuerrero.sanar}.to raise_error NoMethodError

    guerrero.set_method(:potencial_ofensivo,proc{
      1000
    })

    expect(guerrero.potencial_ofensivo).to eq(1000)

    expect(espadachin.potencial_ofensivo).to eq(45)
  end

  it 'Creando un objeto protypable ' do
      brahma = CervezaMagica.new
      brahma.set_property(:gradoDeAlcohol, 134567)
      expect(brahma.gradoDeAlcohol).to eq(134567)

  end
end