FROM node:16 AS build
WORKDIR /app
COPY . . 
RUN npm install
RUN npm run build

FROM nginx
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 3000

