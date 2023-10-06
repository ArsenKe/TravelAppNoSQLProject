# TravelAppNoSQLProject
Domain Description

	The aim of this project is to create a platform where local guides can offer their services as a guided activity for travelers, who visit a certain location at a certain time. A user has a username, email, registration date, password and profile description (bio). Users can be divided into guides and tourists. A tourist has an extra date of birthday attribute. A guide has an average rating of his offered activities which quantifies the quantity and safety their activities.
	Guides can offer multiple activities as service by setting the date of the activity and the location to list (listing date is a timestamp of the listing). An activity can offered by different guides. An activity has an id, a title, description.
	A tourist can look of activities he interested in by the date and location filter attributes. He has also an overview of their booked activities. A booking has an id and a booking date.
Tourist can also share their experiences about the booked activities in the form of a rating. Ratings are tied to the activities they were written to. Therefore removing an activity leads to lose the belonging ratings as well. 


Backend on Intellij:

-clean all the dependancies and install dependencies again
mvn clean install -DskipTests=true

- Builds containers for a service
docker compose up –build

-view all running docker images
docker ps- show running docker images


- terminate running docker files
docker-compose down - terminate the running images


FrontEnd on VS code:

- build react docker image
docker build -t react-image

- run docker image
docker run --rm -p 3000:3000 react-image
