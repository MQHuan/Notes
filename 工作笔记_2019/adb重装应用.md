Before the installation clean the data like this:

adb shell pm clear com.package.foo
then you can install normally using:

adb install foo.apk
or just run through your IDE

https://stackoverflow.com/questions/12483720/adb-how-to-reinstall-an-app-without-retaining-the-data