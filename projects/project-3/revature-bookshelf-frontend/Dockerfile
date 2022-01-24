# nginx image as the base image
FROM nginx:latest
# Copy the build output to replace the default nginx contents.
COPY dist/revature-bookshelf-frontend /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# first compile and build angular code
# To not start from scratch, use node image as base image
FROM node:latest as build

# Then set the working directory
WORKDIR /usr/local/app
#Next add the source code to app
COPY ./ /usr/local/app/

#Install dependencies
RUN npm install
#Generate application build
RUN npm run build
