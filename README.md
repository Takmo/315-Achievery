# Achievery

This is the tale of achievery of a student in CSCE 315.

Haha, this is actually the first assignment in CSCE 315 - Section 502.

This is a magical "achievement tracker" that follows specifications as
defined [on the course website]
(http://ecologylab.net/courses/programmingStudio/assignments/individual1.html).

Please do not copy this for your project. See
[Aggie Honor Code](http://student-rules.tamu.edu/aggiecode) and
[Wheaton's Law](https://twitter.com/wilw/status/5966220832).

Not sure why you might want to, but feel free to use this as you want.
Code freely available under the MIT license. Specifications and reference
website owned and maintained by course instructors for CSCE 315.502 during
the Spring 2016 semester. Used with permission.

## Usage Instructions

The build system I chose to use was the Apache Ant build system. Assuming you have
Apache Ant installed (which is really easy to do!), you can compile everything
by running the following command.

    ant jar

Once everything is compiled, you can run it using a line like this.

    java -jar Achievery.jar < whatever_file_here.txt

Alternatively, if you don't want to install Apache Ant, you can use the makefile.

    make

## Note on Submission Time

Because I'd rather not leave this in a messy state, I'm going to continue working
on this for at least a few minutes after the 5:00pm submission time. If you will accept
this branch as it is (albeit a few minutes after 5:00pm), I would love you forever.

However, if you absolutely must use the repo as of 5:00pm, you'll want to view the
repository as of commit
[e90ccd5](https://github.com/Takmo/315-Achievery/tree/e90ccd569dd67551efb57843e9a591a7561ea384).

## Important Decisions

### Normalization Versus Denormalization

When planning databases, a common topic is data (de)normalization.

Normalization is the process of minimizing data redundancy across a database.
The benefit of this approach is that it can have a massive effect upon the size of the
database and guarantees that write operations (adding data to the database) are
very efficient (write optimized). This comes at the expense of complex read
operations (getting data from the database), which often require complex queries
or multiple read operations in order to locate relevant data.

Example: when marking that a player has earned an achievement, the data only
needs to be recorded in one place. However, when determining which players have
earned a certain achievement, the system iterates through all players to determine
who has played the game in question, then iterates through all achievements that player
has earned in that game to determine if they have unlocked that achievement. Then, with
this data, a full list of players with that achievement can be created.

Denormalization is the process of intentionally adding redundant data to a
database in order to increase the speed of certain read operations. If a player
is awarded an achievement, the record of the player's achievement is recorded
by any combination of the player, the achievement, and the game. The benefit
of this approach is that certain read operations can be made much faster
(read optimized). This comes at the expense of write operations, which must
now update all instances where the data needs to be recorded upon each write
to the database.

Example: when checking to see which players have earned an achievement, the
system can execute exactly one query asking an achivement which players have
unlocked it. However, when marking that a player has unlocked an achievement,
the system must record the award with the player, the achivement, and any other
system that might want to know about this record.

Oftentimes, the decision to normalize or denormalize a database is determined
by the operations you intend to perform on the database. For a database with few
reads and many writes, it often makes sense to keep the data normalized and reduce
complexity. However, for a database with many complex reads and fewer writes,
one is often willing to clutter the layout of their database in order to make
read operations simple and fast.

In this project, because of the operations that must be supported, I have chosen
to take a denormalized approach to constructing this database. The ultimate effect
is that write operations are made more complex and read operations are made less
complex.
