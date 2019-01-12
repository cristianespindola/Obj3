require_relative '../src/prototyped_object'
class PrototypedConstructor
  attr_accessor :prototyped, :block, :constructor_extendido
  def initialize(prototypedOb)
    @prototyped = prototypedOb
    @block = nil
    @referencia = nil
 
  end

  def extended(&block)
    b = self.class.copy(self.prototyped)

    b.block = block
    b.constructor_extendido = self
    return b


  end

  def self.copy(prototype)
    self.new(prototype)
  end

  def self.create (&block)
    prototype = PrototypedObject.new
    prototype.instance_exec(&block)
    self.copy(prototype)
  end

  def with_properties(hash)
    hash.each {|sym| prototyped.set_property(sym, nil)}
    return self
  end
  
  def setearPropiedades(args, obj)
    if args[0].is_a? Hash
      args[0].each {|key, value| obj.set_property(key, value)}
      args.slice!(0, 1)
      obj.set_prototype(self.prototyped)
    end
    if ! self.block.nil?
      obj.instance_exec(*args,&block)
      blockCantArgument = block.arity
      args.slice!(0, blockCantArgument)

    end
    obj.set_prototype(self.prototyped)

  end

  def instanciarMetodosAndProperties(obj)
    instances = prototyped.instance_variables
    instances = instances.slice(2,instances.length)
    instances.each {|instance|
      obj.set_property(instance.slice(1,instance.length).to_sym,prototyped.instance_variable_get(instance))
    }
    self.prototyped.listaDeBloquesYSelector.each {|valor, block| obj.set_method(valor,block)}
    obj.set_prototype(self.prototyped)
  end


  def llamar_logica_construccion(args,protoObj)
    if !constructor_extendido.nil?
      constructor_extendido.llamar_logica_construccion(args, protoObj)
    end
    #si es un mapa, saco un argumento de args
    # Si es un bloque de codigo saco tantos argumentos como arity del bloque
    setearPropiedades(args,protoObj)
  end


  def new(*args)
    a = PrototypedObject.new
    a.set_prototype(prototyped)

    if args.size >=1
      llamar_logica_construccion(args,a)
      #setearPropiedades(args,a)
    #if ! self.block.nil?
      #arguments= args.slice(1, args.length)
      #puts a
      #puts arguments
      #block.call(a,*arguments)
     # a.instance_exec(*arguments,&block)
    #end
    return a
    else
      instanciarMetodosAndProperties(a)
      end
  end

end