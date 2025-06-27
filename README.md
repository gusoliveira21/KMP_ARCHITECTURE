# Mobile App com MVC (Unimodular)

## 🧠 O que é este projeto?
Este é um aplicativo mobile feito com **Kotlin Multiplatform** usando a arquitetura **MVC (Model-View-Controller)**. Ele foi projetado para funcionar em Android e iOS, com **Jetpack Compose** na interface e **Ktor + armazenamento em memória** na camada de dados.

---

## 📦 Como o projeto está organizado?

```
composeApp/
├── data/        → MODELO (dados e lógica de negócio)
├── controller/  → CONTROLLER (liga View e Model)
└── screens/     → VIEW (interface que o usuário vê)
```

### Explicando:
- **Model (dados)**: onde estão os dados da aplicação, como `MuseumObject`, e também os repositórios (`MuseumRepository`).
- **View (telas)**: são as telas que o usuário vê, como `ListScreen` e `DetailScreen`, escritas com Compose.
- **Controller (lógica)**: recebe ações da View e decide o que fazer (ex: buscar dados do repositório).

---

## 🔄 Como o fluxo funciona?

```
Usuário → Tela (View) → Controller → Repositório → API ou Armazenamento → Dados (Model)
```

### Exemplo real:
1. O usuário abre a tela de lista.
2. A `ListScreen` chama o `ListController`.
3. O `ListController` pede dados ao `MuseumRepository`.
4. O repositório busca os dados da memória ou da internet.
5. O resultado volta para a `ListScreen`, que mostra ao usuário.

---

## 📊 Diagrama de Classes (UML)
Representa as estruturas principais do projeto, como classes, atributos e heranças.

<img src='https://raw.githubusercontent.com/gusoliveira21/KMP_ARCHITECTURE/refs/heads/Arquiterura-MVC/Doc/uml.png' width='70%'>

---

## 🔁 Diagrama de Fluxo (Flowchart)
Mostra o caminho que os dados percorrem entre as camadas:

### Modelo detalhado:
<img src='https://raw.githubusercontent.com/gusoliveira21/KMP_ARCHITECTURE/refs/heads/Arquiterura-MVC/Doc/flowChart.png' width='100%'>

---

## 🛠️ Tecnologias Usadas

- **Kotlin Multiplatform**: código compartilhado entre Android e iOS
- **Jetpack Compose**: UI declarativa
- **Koin**: injeção de dependência
- **Ktor Client**: comunicação com a internet
- **Flow e Coroutines**: reatividade e chamadas assíncronas
- **Armazenamento em memória**: rápido e leve para protótipos

---

## ✅ Por que usamos MVC?

- **Fácil de entender**
- **Separação de responsabilidades**
- **Ótimo para apps com lógica leve**
- **Perfeito para projetos compartilhados entre plataformas**

---

## 🤔 Quando usar esse modelo (unimodular)?

Use quando:
- O projeto não é muito grande
- Você quer começar rápido
- A equipe é pequena
- Precisa de algo simples e funcional

---

## 🧩 E se o projeto crescer muito?

Se o projeto ficar maior, você pode:
- Dividir em **módulos separados** (ex: tela de lista, tela de detalhe, domínio, etc)
- Criar camadas extras como `services`, `usecases`, ou até voltar para **Clean Architecture**
