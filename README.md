# Busca Cep SDK

O **SDK Busca Cep** é um projeto desenvolvido visando facilitar a consulta das informações de determinado CEP.

O projeto utiliza a API do **[ViaCEP](https://viacep.com.br)** para realizar as consultas das informações.

## Características

- A consulta dos CEP's é feita utilizando **Coroutines** e **Retrofit** para realizar as requisições a API.
- O SDK realiza o ajuste do CEP para o formato correto, então você pode inserir CEP's nos formatos `01.001-000`, `01001-000`, `01001 000` ou `01001000` e ele realizará a formatação antes de realizar a consulta.
- Além disso o SDK também realiza algumas validações para validar o CEP, como:
    - Verificação de CEP nulo ou vazio
    - Verificação de quantidade de dígitos
    - Verificação se o CEP contém apenas números
- No caso de alguma falha na validação ou na consulta dos dados será retornado um `status`. Esse status pode ser utilizado para controlar a ação em cada caso, cada um dos status acompanha ainda uma mensagem amigável sobre o problema

## Pré-requisitos

Adicione o trecho abaixo no seu arquivo raiz `build.gradle` no final dos seus repositórios:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

## Adicionando o SDK ao seu projeto

Adicione o trecho abaixo no seu arquivo `build.gradle` do seu modulo:

```gradle
dependencies {
    implementation 'com.github.emanuelgalvao:busca-cep:1.0.0'
}
```

## Como usar

Para realizar a consulta você precisa apenas chamar o método `getCepData` da classe `SearchCep` passando o valor do CEP. 

Esse método irá retornar um objeto do tipo `CepCallback`, que pode representar `Success`, `Error` ou `Validation`. A descrição de cada uma das situações vai estar na próxima seção.

**IMPORTANTE: Esse método deve ser chamado em um escopo de Coroutine devido a utilizar uma `suspend fun` para fazer a requisição.**

Exemplo de utilização:

```kotlin
val cep = "01.001-000"

val callback = SearchCep.instance.getCepData(cep)

when (callback) {
    is CepCallback.Success -> {
        handleSuccess(callback.cepModel)
    }
    is CepCallback.Error -> {
        handleError(callback.cepErrorStatus)
    }
    is CepCallback.Validation -> {
        handleValidation(callback.cepValidationStatus)
    }
}
```

## Detalhamento dos callbacks

### CepCallback.Success

Caso ocorra sucesso na consulta será retornado um objeto desse tipo com as informações do CEP na variável `cepModel`.

Modelo do conteúdo da variável `cepModel`:

```kotlin
data class CepModel(val bairro: String,
                    val cep: String,
                    val complemento: String,
                    val ddd: String,
                    val gia: String,
                    val ibge: String,
                    val localidade: String,
                    val logradouro: String,
                    val siafi: String,
                    val uf: String)
```

### CepCallback.Error

Caso ocorra um erro na consulta do CEP relacionado a problemas no servidor ou o CEP ser inexistente será retornado um objeto desse tipo com o status do erro na variável `cepErrorStatus`, a qual possui uma mensagem do motivo do erro.

Possíveis valores da variável `cepErrorStatus`:

| Status | Mensagem |
| --- | --- |
| INVALID_CEP | O CEP informado não foi encontrado. |
| SERVER_ERROR | Ocorreu um erro no servidor. |

### CepCallback.Validation

Caso ocorra um erro na validação do CEP informado antes de realizar a consulta será retornado um objeto desse tipo com o status de validação na variável `cepValidationStatus`, a qual possui uma mensagem do motivo do erro de validação,

Possíveis valores da variável `cepValidationStatus`:

| Status | Mensagem |
| --- | --- |
| CEP_NULL | O CEP informado não pode ser nulo. |
| CEP_EMPTY | O CEP informado não pode ser vazio. |
| CEP_CONTAINS_LETTERS | O CEP informado contém letras ou caracteres especiais. |
| CEP_INCORRECT_LENGTH | O CEP informado deve possuir 8 dígitos. |

## Contribuição

Caso deseje contribuir com esse projeto realize o fork desse repositório e contribua utilizando os [pull requests](https://github.com/emanuelgalvao/busca-cep/pulls).

Qualquer contribuição é bem-vinda.

Caso tenha alguma dúvida ou problema durante a utilização pode abrir uma [issue](https://github.com/emanuelgalvao/busca-cep/issues).
