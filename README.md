# 📱 Mobile App Base com Arquiteturas Kotlin Multiplatform

Este repositório contém **três versões** de um aplicativo mobile construído com **Kotlin Multiplatform**, cada uma implementando uma arquitetura diferente:

- **MVC (Model-View-Controller)**
- **MVVM Unimodular**
- **MVVM Multimodular + Clean Architecture**

O objetivo é fornecer uma base sólida para iniciar projetos mobile multiplataforma com a arquitetura que melhor se adapta à sua realidade.

---

## 🧠 Qual arquitetura escolher?

| Arquitetura           | Quando usar                                                                 |
|-----------------------|------------------------------------------------------------------------------|
| **MVC Unimodular**    | Projetos simples, início rápido, equipe pequena, foco em funcionalidade     |
| **MVVM Unimodular**   | Projetos médios, mais estrutura, testabilidade, separação por camadas       |
| **MVVM Multimodular** | Projetos grandes, equipes maiores, necessidade de escalabilidade e manutenção |

---

## 🔁 Comparativo rápido

| Item                      | MVC                  | MVVM Unimodular       | MVVM Multimodular            |
|---------------------------|----------------------|------------------------|------------------------------|
| Setup inicial             | Rápido               | Médio                  | Mais complexo                |
| Separação de responsabilidades | Básica          | Moderada               | Alta                         |
| Testabilidade             | Média                | Boa                    | Excelente                    |
| Curva de aprendizado      | Baixa                | Moderada               | Alta                         |
| Escalabilidade            | Baixa                | Boa                    | Excelente                    |
| Manutenção a longo prazo | Limitada             | Boa                    | Ideal                        |

---

## 📁 Projetos disponíveis

### 📦 MVC - Arquitetura simples e direta

- Separação clara em `Model`, `View`, `Controller`
- Fácil de compreender e implementar
- Ideal para apps de pequeno porte ou protótipos rápidos

📂 Pasta: `mvvm-arquitetura-mvc`

🔗 Exemplo de fluxo:
```
Usuário → View → Controller → Repository → API ou Storage → Model
```

📊 Diagrama:
![UML](https://raw.githubusercontent.com/gusoliveira21/KMP_ARCHITECTURE/refs/heads/MVVM-Arquitetura-MVC/Doc/uml.png)

📈 Flowchart:
![Fluxo](https://raw.githubusercontent.com/gusoliveira21/KMP_ARCHITECTURE/refs/heads/MVVM-Arquitetura-MVC/Doc/flowChart.png)

---

### 🧩 MVVM Unimodular - Estrutura balanceada

- Baseada em `Compose`, `ViewModel`, `Repository`, `UseCase`
- Ideal para apps de porte médio, com foco em escalabilidade moderada
- Fácil de manter e expandir

📂 Pasta: `mvvm-unimodular`

🔗 Fluxo:
```
UI (Compose) → ViewModel → UseCase → Repository → DataSource
```

📷 Modelo:
<img src='https://raw.githubusercontent.com/gusoliveira21/KMP_ARCHITECTURE/2f6669ce1821ba52684ef19eeff5f9cabefc8dc7/Doc/arch_unit_modulo.svg' width='70%'>

---

### 🧱 MVVM Multimodular + Clean Architecture

- Arquitetura robusta, com separação clara entre camadas (`domain`, `data`, `presentation`)
- Altamente testável, escalável e flexível
- Ideal para grandes equipes e aplicações com ciclo de vida longo

📂 Pasta: `mvvm-multimodular`

🔗 Fluxo:
```
UI (Compose) → ViewModel → UseCase → Repository → DataSource
```

📷 Modelo simples:
<img src='https://raw.githubusercontent.com/gusoliveira21/KMP_ARCHITECTURE/4ee16c99d73a671a21bd43ca86fdf8a39903071c/Doc/arch_simple.svg' width='30%'>

📷 Modelo detalhado:
<img src='https://github.com/user-attachments/assets/830df625-6224-4707-a682-9cefc854c646' width='90%'>

---

## 🚀 Tecnologias Comuns nos Projetos

- **Kotlin Multiplatform (KMP)**
- **Jetpack Compose**
- **Koin** (Injeção de dependência)
- **Ktor Client** (API REST)
- **Coroutines & Flow** (assíncrono e reativo)
- **Armazenamento local (InMemory ou SQLDelight)**

---

## 🤝 Contribuindo

Fique à vontade para clonar, estudar, adaptar ou sugerir melhorias para os projetos. Este repositório foi criado como uma referência prática de diferentes arquiteturas multiplataforma no ecossistema Kotlin.

---

## 📬 Contato
Desenvolvido por [@gusoliveira21](https://github.com/gusoliveira21)

---

Escolha sua arquitetura e comece a construir! 🚀
