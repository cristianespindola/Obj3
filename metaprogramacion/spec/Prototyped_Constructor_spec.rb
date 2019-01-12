require 'rspec'
require_relative '../src/prototyped_object'
require_relative '../src/prototyped_constructor'
describe 'Guerrero' do

  it 'Creando un guerrero con el PrototypedConstructor' do

    guerrero = PrototypedObject.new
    Guerrero = PrototypedConstructor.new(guerrero)
    un_guerrero = Guerrero.new({energia: 100, potencial_defensivo: 30, potencial_ofensivo: 10})
    expect(un_guerrero.energia).to eq(100)
    expect(un_guerrero.potencial_defensivo).to eq(30)
    expect(un_guerrero.potencial_ofensivo).to eq(10)


  end

  it 'Creando un guerrero con un estado basico usando Copy' do
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
    Guerrero = PrototypedConstructor.copy(guerrero)
    un_guerrero = Guerrero.new
    expect(un_guerrero.energia).to eq(100)
    expect(un_guerrero.potencial_defensivo).to eq(10)
    expect(un_guerrero.potencial_ofensivo).to eq(30)
  end


  it 'Creando un guerrero extendido ' do
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
    Guerrero = PrototypedConstructor.copy(guerrero)

    Espadachin = Guerrero.extended {
        | habilidad, potencial_espada|
      set_property(:habilidad, habilidad)
      set_property(:potencial_espada, potencial_espada)

      set_method(:potencial_ofensivo, proc{
        @potencial_ofensivo + self.potencial_espada * self.habilidad
      })

    }


    un_espadachin = Espadachin.new({energia: 100, potencial_defensivo: 30, potencial_ofensivo: 10}, 0.1, 30)
    expect(un_espadachin.energia).to eq(100)
    expect(un_espadachin.potencial_defensivo).to eq(30)
    expect(un_espadachin.potencial_ofensivo).to eq(13.0)
    expect(un_espadachin.potencial_espada).to eq(30)
    expect(un_espadachin.habilidad).to eq(0.1)


    Maestro_espadachin = Espadachin.extended{
        | nivelDeMagia |
      set_property(:nivelDeMagia, nivelDeMagia)
    }


    maestro_roshi = Maestro_espadachin.new({energia: 100, potencial_defensivo: 30, potencial_ofensivo: 10}, 0.1, 30, 45)
    expect(maestro_roshi.energia).to eq(100)
    expect(maestro_roshi.potencial_defensivo).to eq(30)
    expect(maestro_roshi.potencial_ofensivo).to eq(13.0)
    expect(maestro_roshi.potencial_espada).to eq(30)
    expect(maestro_roshi.habilidad).to eq(0.1)
    expect(maestro_roshi.nivelDeMagia).to eq(45)

  end

end