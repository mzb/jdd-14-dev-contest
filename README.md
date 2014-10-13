
## Allegro JDD.14 dev contest - Chief Troublemaker Officer

### Story

You are a team lead in The Company. A new CTO has joined this month.
There are rumors that he is going to fire your colleagues and hire his own.

He calls you to his office.<br/>
He hands you out a piece of paper.<br/>
Damn, that's over, he is going to fire you, but wait!

"This is the new structure of IT Department," he said. "Prepare the contracts for new hires and do something with fired."

"OK boss, no problem, but there are hundreds of guys working here. Who exactly is to be fired? And who is to be hired?"
  
"Listen." He frowns. "You are still a coder, aren't you? Just compare old structure with new one and find the difference!"

### Dev contest
**Your task** is to implement a diff method for comparing two trees with the employee structure.

Method that you should fill in, is in the [`StructureDiff`](src/main/java/pl/allegro/jdd/StructureDiff.java) class.

As a result, the method should return an [`Changes`](src/main/java/pl/allegro/jdd/Changes.java) with three sets:

* fired guys,
* hired guys 
* and the guys whose salary has changed.

```java
public class StructureDiff {
    public Changes calculate(Employee oldCTO, Employee newCTO){
        // your code goes here
        throw new NotImplementedException();
    }
}
```

We have also prepared bunch of failing tests to make your life easier.

**Tools**

*  Java 8, Groovy, Gradle
*  any library you need

**Criteria**
   
* code passes all unit tests and is fast
* make your code small, clean and sexy
* leverage lambdas or maybe use some smart tool?

**Applying**

*  fork this repository [https://github.com/allegro/jdd-14-dev-contest](https://github.com/allegro/jdd-14-dev-contest) 
*  code!
*  push a solution to master branch on your fork 
*  send a short email to `allegrotech.io@allegro.pl`, with url of your fork  
*  competition ends at **15:00, October 14'th**,
   the winners will be announced at Allegro booth at **16:55, October 14'th**  

**Jury**

* [Bartosz Walacik](https://github.com/bartoszwalacik/)
* [Piotr Betkier](https://github.com/pbetkier)
* [Adam Dubiel](https://github.com/adamdubiel)

**Tips**

Come to our booth for some tips. 

**Fame**

The best solutions might be published on [allegrotech.io/blog](http://allegrotech.io/blog).

**Awards**

 * tablet Nexus 7 for the best solution
 * Dice+, booby prize
 
<br/>
<img src="logo-allegro-tech.png"/><br/>
[allegrotech.io](http://allegrotech.io)






