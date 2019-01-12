module Prototyped

    attr_accessor :prototype, :listaDeBloquesYSelector

    @listaDeBloquesYSelector = Array.new

    def method_missing(sym, *args, &block)
      if (prototype.nil?)
        crear_Metodo_Si_El_Sym_Termina_Con_Igual(sym,*args)
      else
        if (prototype.methods.include?(sym))
          bloque = detectar_Bloque_Para_Un_Sym_Determinado(sym)
          self.instance_exec(*args, &bloque)
        else
          prototype.method_missing(sym, *args, &block) #
        end
      end
    end

    def detectar_Bloque_Para_Un_Sym_Determinado(sym)
      hash1 = prototype.listaDeBloquesYSelector.select {|syms, bloque| syms.to_s == sym.to_s}
      l = hash1.length
      b = hash1[l - 1]
      b[1]
    end

    def crear_Metodo_Si_El_Sym_Termina_Con_Igual(sym, *args)
      string = sym.to_s[sym.to_s.length - 1]
      if string == "="
        createDefinition(sym, *args)
      else  raise NoMethodError
      end
    end

    def createDefinition(sym, *args)
      arg = args[0]
      selector = sym.to_s.slice(0, sym.to_s.length - 1)
      if !arg.is_a? Proc
        self.set_property(selector, arg)
      else
        self.set_method(selector, arg)
      end
    end
    def agregarBLoqueYSym(name,block)
      #Agrega un par de selector y su respectivo bloque a un lista
      par = name,block
      listaDeBloquesYSelector.push(par)
    end


    def set_property(prop_name, prop_value)
      self.instance_variable_set("@#{prop_name}", prop_value)

      self.set_method(prop_name, proc {
        self.instance_variable_get("@#{prop_name.to_s}")
      })

      self.set_method((prop_name.to_s + "=").to_sym, proc {
          |another_value|
        self.instance_variable_set("@#{prop_name}", another_value)
      })
    end

    def set_method(name, block)
      self.agregarBLoqueYSym(name,block)
      self.define_singleton_method(name, &block)

    end

    def set_prototype(prototype)
      self.prototype = prototype

    end

    def set_prototypes(arrayP)
      prototype
    end

    def call_next(sym)
      if prototype.methods.include?(sym)
        bloque = detectar_Bloque_Para_Un_Sym_Determinado(sym)
        #puts bloque
        #puts prototype
        prototype.instance_exec(&bloque)
      else prototype.call_next(sym)
      end
    end

end