homework for algorithms course at https://www.coursera.org/learn/algorithms-part1

https://github.com/adoptium/temurin11-binaries/releases/download/jdk-11.0.16%2B8/OpenJDK11U-jdk_aarch64_mac_hotspot_11.0.16_8.pkg

```
sudo mkdir /usr/local/algs4
cd /usr/local/
sudo chmod 755 algs4
cd algs4
sudo curl -O "https://algs4.cs.princeton.edu/code/algs4.jar"
sudo curl -O "https://algs4.cs.princeton.edu/linux/{javac,java}-{algs4,cos226,coursera}"
sudo chmod 755 {javac,java}-{algs4,cos226,coursera}
sudo mv {javac,java}-{algs4,cos226,coursera} /usr/local/bin

sudo curl -O "https://algs4.cs.princeton.edu/linux/findbugs.{zip,xml}"
sudo curl -O "https://algs4.cs.princeton.edu/linux/findbugs-{algs4,cos226,coursera}"
sudo unzip findbugs.zip
sudo chmod 755 findbugs-{algs4,cos226,coursera}
sudo mv findbugs-{algs4,cos226,coursera} /usr/local/bin

sudo curl -O "https://algs4.cs.princeton.edu/linux/pmd.{zip,xml}"
sudo curl -O "https://algs4.cs.princeton.edu/linux/pmd-{algs4,cos226,coursera}"
sudo unzip pmd.zip
sudo chmod 755 pmd-{algs4,cos226,coursera}
sudo mv pmd-{algs4,cos226,coursera} /usr/local/bin

sudo curl -O "https://algs4.cs.princeton.edu/linux/checkstyle.zip"
sudo curl -O "https://algs4.cs.princeton.edu/linux/checkstyle-suppressions.xml"
sudo curl -O "https://algs4.cs.princeton.edu/linux/checkstyle-{algs4,cos226,coursera}.xml"
sudo curl -O "https://algs4.cs.princeton.edu/linux/checkstyle-{algs4,cos226,coursera}"
sudo unzip checkstyle.zip
sudo chmod 755 checkstyle-{algs4,cos226,coursera}
sudo mv checkstyle-{algs4,cos226,coursera} /usr/local/bin
```

in .bash_profile or .zshrc
```
export JAVA_HOME=`/usr/libexec/java_home -v 1.8.0_345`
```

to compile with algs4 `javac-algs4 RandomWord.java`
to run with algs4 `java-algs4 RandomWord`
