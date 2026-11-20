# Enterprise Selenium + Cucumber + TestNG Framework

## Run locally
mvn clean test -Denv=qa -Dbrowser=chrome -Dexecution=local -Dheadless=true

## Run selected tags
mvn clean test -Denv=qa -Dbrowser=chrome -Dexecution=local -Dcucumber.filter.tags="@smoke"

## Run against Selenium Grid
docker compose up -d
mvn clean test -Denv=qa -Dbrowser=chrome -Dexecution=grid -Dheadless=true
docker compose down

## Architecture
Jenkins -> Maven -> TestNG/Cucumber -> Step Definitions -> TestContext ->
Page/API/DB layer -> Selenium/REST Assured/JDBC

## Important
- Replace example URLs and credentials before use.
- Do not commit real passwords/tokens.
- Add the JDBC driver required by your actual database.
- Verify Docker image versions against the Selenium version used in your environment.
