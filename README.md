Connect Four
-------------------
A Java implementation of [Connect Four game](https://github.com/chenglong-liu/connect-four/blob/master/connect4-question-description.md).

1. SampleRunMain.main() can do sample run automatically, players have predefined choices.
2. ConnectFourMain.main() takes choices from console, you can play with it.

Default Setting
-------------------
* Board has 6 rows and 7 columns.
* Winning condition is 4 same discs in a straight vertical, horizontal or diagonal line.
* Players number is 2.

You can change default setting as you wish.

To get the code:
-------------------
Clone the repository:

    $ git clone git://github.com/chenglong-liu/connect-four.git

To run the application:
-------------------
From the command line with Maven:

    $ cd connect-four
    $ mvn compile
    $ mvn exec:java -Dexec.mainClass="com.ubs.connectfour.SampleRunMain"
    $ mvn exec:java -Dexec.mainClass="com.ubs.connectfour.ConnectFourMain"

or in your preferred IDE:

* Import connect-four as a Maven Project.
* Run SampleRunMain.main()
* Or run ConnectFourMain.main()

