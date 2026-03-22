FROM node:lts-alpine

RUN apk --no-cache add curl

WORKDIR /app
COPY pack*.json ./
COPY tsconfig*.json ./
COPY eslint.config.js eslint.config.js
COPY vite.config.ts vite.config.ts
COPY .env .env

RUN npm i
COPY src src
COPY public public
COPY index.html index.html

CMD ["npm", "run", "dev", "--", "--host"]
