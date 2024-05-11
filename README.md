[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/zkQ7Yn-Y)
<h1> Assignment 3 </h1>
<h3> Testing and AOP. </h3>

Dear students, <br />

In this assignment, you are required to create a very simple RESTful application using Spring similar to the previous
Assignment 2. (One entity and CRUD operations on that entity is enough - use GET, POST, DELETE,  PUT, PATCH methods). 

<h4> Task related: (20%)</h4>
<ul>
    <li> Create a CRUD for a single entity</li>
    <li> Perform a validation for data entry in create and update</li>
    <li> Use DTO pattern instead of exposing your entities to the client</li>
    <li> Perform dynamic and automatic mapping using MapStruct instead of manual mapping</li>
</ul>

<h4> Task related: (20%)</h4>
<ul>
    <li> Write unit tests to test methods of Service class(es). Try to design test scenarios as rich as possible</li>
    <li> Write some integration tests for Controller methods</li>
</ul>

<h4> Task related: (20%)</h4>
<ul>
    <li> Log a message before each method is called in the Service class(es) with the information about the input parameters</li>
    <li> Log a message after each method is executed in the Service class(es) with the information about the return value</li>
    <li> Log a message after each method is executed in the Service class(es) with the information about the execution time</li>
</ul>

<h4> Task related: (30%)</h4>
<ul>
    <li> Develop another app which will use the endpoints exposed by the first app listening to different port. </li>
    <li> Develop endpoints on the new app to send requests to the former one and redirect the responses back to the user. </li>
    <li> Apply logging again for the new app. </li>
</ul>

<h4> Bonus: (20%)</h4>
<ul>
    <li> There are two applications now that are running on your machine. </li>
    <li> Dockerize the applications: 
        <ul>
            <li> Create Dockerfile for both applications and build the docker image for both. </li>
            <li> Run both images to create containers and check both applications.</li>
            <li> Running multiple images manually is not very practical. Create docker-compose file to run your containers in an easier way.  </li>
        </ul>
    </li>
</ul>

<h4> Submission related: (10%) </h4>
<ul>
    <li> Make sure you configured your git client (i.e., username and email is set). Use firstname_lastname as the
        username. </li>
    <li> Please, also ensure that you regularly check, add and commit your changes to the remote repo so that we can
        see
        your progress. </li>
    <li>Things to be submitted to the BB grader in a .zip format:
        <ol>
            <li>This README.md file updated to have
                <ul>
                    <li>the instructions on how to start and use the application(s)</li>
                    <li>link to the video recording</li>
                </ul>
            </li>
            <li>Application source after <em>./gradlew clean</em></li>
        </ol>
    </li>
    <li> <strong> Submissions without a video submission will not be graded.</strong> </li>
</ul>

<p><b>Note:</b> Please work every day; this is definitely not a task you can complete on the last day!
    To keep track of the progress, create a git repository, commit, and push each change (ideally at least every time
    you complete a feature) to the repository.
    Ensure that you have at least <strong>1 commit a day</strong> STARTING from <strong>May 9<sup>th</sup></strong>
    otherwise you will lose points.
</p>
<p>For <strong>late submissions</strong> please refer to the SYLLABUS.</p>


This assignment will give you <strong>10%</strong> of the total. <br />
<em> Good luck! </em> <br />
<em> NS </em>
