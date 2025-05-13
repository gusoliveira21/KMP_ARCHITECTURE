# Mobile App com MVVM (Unimodular)

## Visão Geral
Arquitetura MVVM unimodular para aplicações mobile usando Kotlin Multiplatform, focada no módulo composeApp. Ideal para projetos que necessitam de uma base sólida, escalável e testável.

## Estrutura do Projeto
```
composeApp/
├── src/
│   ├── commonMain/
│   │   ├── domain/      # Regras de negócio e modelos
│   │   ├── data/        # Repositórios e fontes de dados
│   │   └── presentation/# UI e ViewModels
│   ├── androidMain/
│   └── iosMain/
```

## Fluxo de Dados
```
UI (Compose) → ViewModel → UseCase → Repository → DataSource
```

## Tecnologias Principais
- Kotlin Multiplatform
- Jetpack Compose
- Kotlin Coroutines & Flow
- Ktor Client
- Koin
- SQLDelight
- DataStore

## Padrões de Design
- **MVVM**: Separação UI/lógica, gerenciamento de estado via StateFlow
- **Repository**: Abstração de fontes de dados, cache inteligente
- **UseCase**: Encapsulamento de regras de negócio
- **Clean Architecture**: Separação em camadas, independência de frameworks

## Vantagens
- Código organizado e previsível
- Fácil manutenção e testabilidade
- Desenvolvimento ágil
- Baixo acoplamento
- Alta coesão

## Comparativo: Unimodular vs Multimodular

### Unimodular (Este Projeto)
**Prós**:
- Setup rápido e simples
- Menor complexidade
- Desenvolvimento ágil
- Ideal para equipes menores

**Contras**:
- Menor isolamento entre camadas
- Pode ficar complexo em projetos muito grandes

### Multimodular
**Prós**:
- Maior isolamento entre features
- Mais escalável para projetos grandes
- Permite desenvolvimento paralelo

**Contras**:
- Setup inicial complexo
- Maior overhead de configuração
- Curva de aprendizado mais íngreme

## Quando Usar
**Escolha Unimodular quando**:
- Projeto de médio porte
- Equipe pequena
- Necessidade de desenvolvimento rápido
- Foco em simplicidade

**Escolha Multimodular quando**:
- Projeto de grande porte
- Equipe grande
- Necessidade de isolamento entre features
- Desenvolvimento paralelo de features
