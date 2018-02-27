<p>An application to demonstrate the functionality of the following RESTful endpoints</p>
      <pre><code> GET localhost:8081/films
             lists all films and relevant metadata</code></pre>	
       <pre><code>GET localhost:8081/films/:id
               lists a single film, given the id</code></pre>	
       <pre><code>POST localhost:8081/rating/:id
	       retrieves rating of single film if available
	       also retrieves films metadata 
	       accepts rating of single film from user</code></pre> 
<p>How to access this application </p>
    <pre><code>Run ./gradlew build in terminal in the location of the project repository
    Copy & paste any of the URI's into browser</code></pre>
<p>How to run the tests for this application </p>
    <pre><code>Run ./gradlew test in terminal in the location of the project repository</code></pre> 
