coin manager-back-prisma
Back-end for coin manager, an event management solution.

About
coin manager is personal finance management app that provides a simple way to streamline your finances, tracking your income and expenses

link for the frontend of the project: https://github.com/rafaelor20/coin-manager-front-end

Diagram Classes


classDiagram
    class User {
        +id: Int
        +username: String
        +password: String
        +email: String
        +createdAt: DateTime
        +sessions: Session[]
        +transactions: Transaction[]
        +userDebts: UserDebt[]
        +userCredits: UserCredit[]
    }

    class Session {
        +id: Int
        +userId: Int
        +token: String
        +createdAt: DateTime
    }

    class Transaction {
        +id: Int
        +userId: Int
        +description: String
        +amount: Float
        +date: DateTime
        +category: String
    }

    class UserDebt {
        +id: Int
        +userId: Int
        +creditor: String
        +amount: Float
        +createdAt: DateTime
        +payDate: DateTime
    }

    class UserCredit {
        +id: Int
        +userId: Int
        +debtor: String
        +amount: Float
        +createdAt: DateTime
        +payDate: DateTime
    }

    User "1" --o "1..*" Session : has
    User "1" --o "1..*" Transaction : has
    User "1" --o "1..*" UserDebt : has
    User "1" --o "1..*" UserCredit : has




Endpoints
POST "/auth/sign-in" Body: { email, password } Response: { user: exclude(user, 'password'), token, };

GET "/credits" Response:[ { id, user_id, debtor, amount, createdAt, payDate, }, ]

POST "/credits/store" Body: { user_id, debtor, amount, payDate } Response: { id, user_id, debtor, amount, createdAt, payDate, }

GET "/credits/:creditId" Response: { id, user_id, debtor, amount, createdAt, payDate, }

DELETE "/credits/delete/:creditId" Response: { id, user_id, debtor, amount, createdAt, payDate, }

GET "/debts" Response: [ { id, user_id, creditor, amount, createdAt, payDate, }, ]

GET "/debts/:debtId" Response: { id, user_id, creditor, amount, createdAt, payDate, }

POST "/debts/store" Body: { user_id, creditor, amount, payDate } Response: { id, user_id, creditor, amount, createdAt, payDate, }

DELETE "/debts/delete/:debtId" Response: { id, user_id, creditor, amount, createdAt, payDate, }

GET "/transactions/historic" Response: [ { id, user_id, description, amount, date, category, }, ]

POST "/transactions/store" Body: { description, amount, category } Response: { id, user_id, description, amount, date, category, }

POST "/users" Body: { username, email, password } Response: { id, email, username, }

How to run for development
Clone this repository
Install all dependencies
npm i
Create a PostgreSQL database with whatever name you want
Configure the .env.development file using the .env.example file (see "Running application locally or inside docker section" for details)
Run all migrations
npm run dev:migration:run
Seed db
npm run dev:seed
Run the back-end in a development environment:
npm run dev
How to run tests
Follow the steps in the last section
Configure the .env.test file using the .env.example file (see "Running application locally or inside docker" section for details)
Run all migrations
npm run migration:run
Run test: (locally)
npm run test
(docker)

npm run test:docker
Building and starting for production
npm run build
npm start
Running migrations or generate prisma clients
Before running migrations make sure you have a postgres db running based on .env.development or .env.test file for each environment. You can start a postgres instance by typing npm run dev:postgres or npm run test:postgres. The host name is the name of the postgres container inside docker-compose file if you are running the application inside a docker container or localhost if you are running it locally.

You can operate on databases for different environments, but it is necessary to populate correct env variables for each environment first, so in order to perform db operations type the following commands:

npm run dev:migration:run - run migrations for development environment by loading envs from .env.development file. It uses dotenv-cli to load envs from .env.development file.

npm run test:migration:run - the same, but for test environment

npm run dev:migration:generate -- --name ATOMIC_OPERATION_NAME - generate and run migration and prisma client for development environment by loading envs from .env.development file. Replace ATOMIC_OPERATION_NAME by the name of the migration you want to generate.

Switching between environments
In order to switch between development and test environments you need to shutdown the current postgres instance if it is running and start the new one.

If you are in development environment:

npm run dev:postgres:down
And then

npm run test:postgres
If you are in test environment:

npm run test:postgres:down
And then

npm run dev:postgres
Running application locally or inside docker
.env.development and .env.test must be changed if you and to run the application locally or inside docker. You can populate files based on .env.example file, but you need to consider the following:

Running application locally (postgres and node):
Add your postgres credentials and make sure to create given database before running the application.

Running application inside docker (postgres and node):
Set POSTGRES_HOST to drivent-postgres-development for .env.development and drivent-postgres-test for .env.test file. It is the name of the postgres container inside docker-compose file. Docker Compose will start the postgres container for you, create the database and host alias for you.

Running application locally (node) but postgres is running inside docker:
Set POSTGRES_HOST to localhost for .env.development and localhost for .env.test file. Docker compose is configured to expose postgres container to your localhost.

What to do when add new ENV VARIABLES
There are several things you need to do when you add new ENV VARIABLES:

Add them to .env.example file
Add them to your local .env.development and .env.test files
Add them to your docker-compose.yml file (just the name, not the value). Only envs listed in the environment section will be exposed to your docker container.
Add them (prod version) to your github repo secrets. They will be used to generate the .env file on deploy.
Add them (prod version) to test.yml file on .github/workflows/test.yml.