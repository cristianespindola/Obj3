require 'rspec'
require_relative '../src/prototyped_object'
require_relative '../src/prototyped_constructor'

describe 'Call_Next And Prototypes' do

  it 'Verificando la funcionalidad del metodo call_next 'do
    guerreroVis = PrototypedObject.new
    guerreroVis.set_property(:energia,100)
    expect(guerreroVis.energia).to eq(100)
    guerreroVis.set_property(:potencial_defensivo,10)
    expect(guerreroVis.potencial_defensivo).to eq(10)
    guerreroVis.set_property(:potencial_ofensivo,30)
    expect(guerreroVis.potencial_ofensivo).to eq(30)

    espadachin = PrototypedObject.new
    espadachin.set_prototype(guerreroVis)
    espadachin.set_property(:habilidad, 0.5)
    espadachin.set_property(:potencial_espada, 30)
    espadachin.energia = 100
    espadachin.potencial_defensivo = 10
    espadachin.potencial_ofensivo = 60
    expect(espadachin.habilidad).to eq(0.5)
    expect(espadachin.potencial_espada).to eq(30)
    expect(espadachin.energia).to eq(100)
    expect(espadachin.potencial_defensivo).to eq(10)

    expect(espadachin.potencial_ofensivo).to eq(60)
    expect(guerreroVis.potencial_ofensivo).to eq(30)

    expect(espadachin.call_next(:potencial_ofensivo)).to eq(30)
    puts (espadachin.call_next(:potencial_ofensivo))

    espadachin.potencial_ofensivo =
        espadachin.call_next(:potencial_ofensivo) + espadachin.potencial_espada * espadachin.habilidad

    expect(espadachin.potencial_ofensivo).to eq(45.0)


  end
  it 'Creando un espadachin con la implementacion de un metodo anterior usando call_next'do
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

    un_guerrero = Guerrero.new({energia: 100, potencial_defensivo: 30, potencial_ofensivo: 10})
    expect(un_guerrero.energia).to eq(100)
    expect(un_guerrero.potencial_defensivo).to eq(30)
    expect(un_guerrero.potencial_ofensivo).to eq(10)


    Espadachin = Guerrero.extended {
        |  una_habliidad, una_potencia_espada|
      self.habilidad = una_habliidad
      self.potencia_espada = una_potencia_espada

      self.potencial_ofensivo =
         self.call_next(:potencial_ofensivo) + self.potencia_espada * self.habilidad

    }
    un_espadachin = Espadachin.new({energia: 100, potencial_defensivo: 30, potencial_ofensivo: 10}, 0.1, 30)
    expect(un_espadachin.potencial_ofensivo).to eq(13)
    expect(un_guerrero.potencial_ofensivo).to eq(10)
    puts un_espadachin.call_next(:potencial_ofensivo)
    puts un_espadachin.prototype.send(:potencial_ofensivo)
    puts Guerrero.send(:potencial_ofensivo)
    puts un_guerrero.send(:potencial_ofensivo)
  end
end