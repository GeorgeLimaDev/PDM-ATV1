package br.ifpb.pdm

import kotlin.reflect.typeOf

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 7) {
        menu()
        print("Digite a opção: ")
        val input = readLine()
        opcao = input?.toIntOrNull() ?: 0
        when (opcao) {
            1 -> {
                val animal = criarCachorro()
                repositorioAnimal.adicionar(animal)
            }
            2 -> {
                val gato = criarGato()
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                val passaro = criarPassaro()
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                repositorioAnimal.listar()
            }
            5 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
            }
            6 -> {
                println("Nome do animal a remover: ")
                val nomeAnimal = readLine()
                repositorioAnimal.remover(nomeAnimal ?: "")
            }
            7 -> println("Saindo do programa...")
            8 -> {
                val homem = criarHomem()
                repositorioAnimal.adicionar(homem)
            }
            else -> println("Opção inválida!")

        }
    }
}

fun criarCachorro(): Cachorro {
    println("Digite o nome do cachorro: ")
    val nome = readLine()
    println("Digite a idade do cachorro: ")
    val idade = readLine()?.toIntOrNull()
    println("Digite a cor do cachorro: ")
    val cor = readLine()
    return Cachorro(idade ?: 0, cor ?: "").apply { this.nome = nome ?: "" }
}

fun criarGato(): Gato {
    println("Digite o nome do gato: ")
    val nome = readLine()
    println("Digite a idade do gato: ")
    val idade = readLine()?.toIntOrNull()
    println("Digite a cor do gato: ")
    val cor = readLine()
    return Gato(idade ?: 0, cor ?: "").apply { this.nome = nome ?: "" }
}

fun criarPassaro(): Passaro {
    println("Digite o nome do pássaro: ")
    val nome = readLine()
    println("Digite a idade do pássaro: ")
    val idade = readLine()?.toIntOrNull()
    println("Digite a cor do pássaro: ")
    val cor = readLine()
    return Passaro(idade ?: 0, cor ?: "").apply { this.nome = nome ?: "" }
}

fun criarHomem(): Homem {
    println("Digite o nome do homem: ")
    val nome = readLine()
    println("Digite a idade do homem: ")
    val idade = readLine()?.toIntOrNull()
    println("Digite a cor do homem: ")
    val cor = readLine()
    return Homem(idade ?: 0, cor ?: "").apply { this.nome = nome ?: "" }
}



abstract class Animal(var idade: Int, var cor: String) {
    enum class Cores {
        Preto, Branco, Marrom, Outra
    }

    open var nome: String = ""
        get() = "Animal: $field"
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos(): Int {
        return idade * 7
    }
}

class Cachorro(idade: Int, cor: String) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }

    override fun emitirSom() {
        println("Au au")
    }
}

class Gato(idade: Int, cor: String) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: String) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

class Homem(idade: Int, cor: String) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Salve!")
    }

    override fun idadeEmAnosHumanos(): Int {
        return super.idadeEmAnosHumanos() / 7
    }
}

fun menu() {
    println("1 - Cachorro")
    println("2 - Gato")
    println("3 - Pássaro")
    println("4 - Listar Animais")
    println("5 - Emitir som")
    println("6 - Remover Animal")
    println("7 - Sair")
    println("8 - Homem")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun buscarPorNome(nomeAnimal: String): Animal? {
        var teste = animais.find { it.nome == nomeAnimal }
        return animais.find { it.nome == nomeAnimal }
    }

    fun remover(nomeAnimal: String) {
        val animalARemover = buscarPorNome(nomeAnimal)
        if (animalARemover != null) {
            animais.remove(animalARemover)
            print("Animal $animalARemover removido")
        }
        else {
        print("Animal $animalARemover não encontrado")
        }
    }

    fun listarPorCor(cor: String) {
        val animaisCor = animais.filter { it.cor.toString() == cor.uppercase() }
        animaisCor.forEach { println(it.nome) }
    }

    fun listarPorIdade(idade: Int) {
        val animaisIdade = animais.filter { it.idade == idade }
        animaisIdade.forEach { println(it.nome) }
    }

}
