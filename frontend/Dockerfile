# FROM node:current-alpine
# WORKDIR /app

# COPY package.json ./
# RUN npm install

# COPY . .
# EXPOSE 3000
# CMD ["npm","start"]


# pull official base image
FROM node:13.12.0-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent
RUN npm install -g --silent

# add app
COPY . ./

# start app
CMD ["npm", "start"]