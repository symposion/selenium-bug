# Selenium attribute mangling bug

`mvn clean test` to run

Demonstrates that recent chromedriver/selenium combo results in a mangling of href attributes when calling `getAttribute` (or `getDomProperty`) but not when calling `getDomAttribute`. I cannot see anything in any of the relevant specifications that would justify doing this kind of processing on the attribute value irrespective of whether it is accessed as a property or an attribute. 

I guess this is probably a Chrome(driver) bug but I'm not a position to determine that with certainty. 