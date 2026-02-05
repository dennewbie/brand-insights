# brand-insights

## How to Run

1) Clone the repository to your local machine.

2) Ensure you have Java 17+ and Maven installed.

3) Run the application using the command: mvn spring-boot:run.

4) The system includes a MockCrawlerService that automatically generates brand mentions every 5 seconds.

5) View items flagged for review at: GET /api/items/pending-review.
6) Approve items via: POST /api/items/{id}/approve.

## Architectural Choices
The project implements a Chain of Responsibility pattern for the data enrichment pipeline. This was chosen to ensure a clean separation of concerns, where each "Enricher" (Sentiment, Image Analysis) operates independently on the data.

A Service-driven Orchestrator manages the workflow and routing logic. It evaluates the results of the pipeline and determines if an item is "Clean" (Finalized) or requires "Manual Review" (Pending) based on predefined business rules, such as negative sentiment detection.

H2 In-Memory Database is used to provide a full SQL environment for analytics without requiring any external infrastructure setup, making the prototype highly portable.

## Extensibility
To extend the system with new processing steps (e.g., Language Translation or Keyword Extraction), you only need to create a new class that implements the ItemEnricher interface and annotate it with @Component. Spring will automatically detect and inject it into the pipeline.

The routing logic can be easily modified in the PipelineOrchestrator to include more complex "Human-in-the-loop" triggers, such as flagging items from specific URLs or those containing specific detected entities.