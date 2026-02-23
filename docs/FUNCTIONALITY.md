# Chatbot — Functionality Documentation

## Overview

This project is a **console-based chatbot** built for training purposes. It uses **Spring Boot** (with data source and JPA auto-configuration disabled for the current console flow) and follows a **hexagonal architecture** (ports and adapters) to separate domain logic from infrastructure.

The bot runs in the terminal: users type messages and receive replies based on the current **conversation state** (e.g. greeting and asking for the user’s name).

---

## Main Functionality

### User flow

1. **Start** – The application starts a single **ChatBot** instance (e.g. "Botcito"), creates one **User**, and launches a **console** that reads from standard input.
2. **First message** – When the user sends any first message in a new conversation, the bot replies: *"Hola, cuál es tu nombre"*.
3. **Name** – The next message is treated as the user’s name. The bot replies: *"Hola, [name]"*.
4. **Further messages** – After that, the conversation is in `IN_PROGRESS`. The current implementation does not define extra behavior for additional messages (the default branch returns *"Error."*).

So the **implemented functionality** is: **greet → ask for name → greet by name**.

### Conversation states

Conversations advance through these states (see `ConversationState`):

| State              | Description                                      |
|--------------------|--------------------------------------------------|
| `NEW`              | Just created; first user message triggers greeting and name question. |
| `WAINTING_FOR_NAME`| Bot asked for name; next message is stored as user name. |
| `WAITING_FOR_CHAT` | Reserved for future use.                         |
| `IN_PROGRESS`      | Name received; general chat (currently minimal). |
| `CLOSE`            | Reserved for future use.                         |

State transitions are handled inside `Conversation.handleUserMessage(String message)`.

---

## Architecture

### Hexagonal structure

- **Domain** – Core entities and rules (no framework or I/O).
- **Application** – Use cases and **input ports** (what the application can do).
- **Infrastructure** – **Adapters**: input (e.g. console), output (e.g. repositories).

### Components

#### Domain (`domain.model`)

- **ChatBot** – Identified by `chatId` and `chatName`. Holds a map of **User**s. Entry point for handling messages: `handleUserMessage(userId, message)` delegates to the user and returns the bot’s reply.
- **User** – Identified by `userId`. Owns a list of **Conversation**s. “Current” conversation is the last one; if there is none, a new conversation is started. Forwards messages to `getCurrentConversation().handleUserMessage(message)`.
- **Conversation** – Identified by `conversationId`. Has `conversationState` and `userName`. Implements the state machine: NEW → WAINTING_FOR_NAME → IN_PROGRESS and returns the appropriate **ConversationResponse**.
- **ConversationState** – Enum: NEW, WAINTING_FOR_NAME, WAITING_FOR_CHAT, IN_PROGRESS, CLOSE.
- **ConversationResponse** – Wraps the reply `message` returned to the user.
- **NewConversation** – Interface for conversation-related data (state, id, user message); not yet fully used in the main flow.
- **MessageProvider** – Interface for providing messages (e.g. welcome); defines `getWelcomeMessage()`.

#### Application (`application`)

- **HandleUserMessageInputPort** – Input port: `String handleUserMessage(UUID userId, String input)`.
- **HandleUserMessageUseCase** – Implements the port by delegating to `ChatBot.handleUserMessage(userId, input)`.

#### Infrastructure – Input adapters (`infrastructure.adapter.in`)

- **ConsoleInputAdapter** – Reads lines from `System.in` with a `Scanner`, sends each line to `HandleUserMessageInputPort.handleUserMessage(consoleUser.getUserId(), input)`, and prints the returned string to the console. Runs in an infinite loop.
- **HelloMessages** – Loads messages from `src/main/resources/Messages.json` (e.g. for welcome messages). Not wired into the main conversation flow yet.

#### Infrastructure – Output adapters / ports

- **ChatBotRepository** (port) – Declares `createUser(String userMessage)`; no implementation behavior yet.
- **InMemoryUserRepository** – Placeholder implementation; not yet used in the main flow.

---

## Application startup

In `ChatbotApplication`:

1. Spring Boot is started (e.g. for future web/Kafka/JPA use).
2. A **ChatBot** is created (e.g. name `"Botcito"`).
3. One **User** is created and registered with the chatbot.
4. **HandleUserMessageUseCase** is instantiated with that **ChatBot**.
5. **ConsoleInputAdapter** is created with that use case and **start(consoleUser)** is called, so the console loop runs for that user.

So currently there is **one bot, one user, one console** per run.

---

## How to run

From the project root (where `pom.xml` is):

```bash
mvn spring-boot:run
```

Or build and run the packaged application:

```bash
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Then type in the terminal. Example:

- You: `hola`  
  Bot: `Hola, cuál es tu nombre`
- You: `María`  
  Bot: `Hola, María`
- You: `otro mensaje`  
  Bot: `Error.` (no specific behavior for IN_PROGRESS yet)

---

## Technology stack

- **Java 25**
- **Spring Boot 4.0.2** (Web MVC, JPA, Kafka, Validation available; DataSource and Hibernate JPA auto-configuration are excluded for the current console-only setup)
- **Maven** for build and dependencies

---

## Possible extensions (from current code)

- Use **MessageProvider** / **HelloMessages** to send welcome messages from `Messages.json`.
- Implement behavior for **IN_PROGRESS** (and optionally **CLOSE**, **WAITING_FOR_CHAT**).
- Use **ChatBotRepository** / **InMemoryUserRepository** to persist or look up users.
- Add more input adapters (e.g. REST API, Kafka consumers) using the same **HandleUserMessageInputPort**.

---

*Documentation generated from the current codebase. Conversation flow and state logic are implemented in `Conversation.handleUserMessage` and used via `ChatBot` → `User` → `Conversation`.*
