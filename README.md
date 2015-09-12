# Mockcrumb - data mocking library

## What is this?

Mockcrumb helps you to mock data and load them from various data sources.

## How does it work?

You write your unit tests. You are fed up with creating mock POJO classes. Mockcrumb gives you a hand to load them from any data source you like.

Let's say that you need an instance of your favourite Foo POJO:

```java
public class Foo {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
```

Which is used inside your test case class:

```java
public class FooTest {
	private Foo foo1;
    
    @Before
    public void init() {
        foo1 = new Foo();
        foo1.setValue("foo1Value");
    }
    
    @Test
    public valueTest() {
    	assertThat(foo1.getValue()).isEqualTo("foo1Value");
    }
}
```

Usually you have to create mock objects manually. It's common to create utility test classes which create mock object for various tests.

With mockcrumb the only thing you have to do is to defined initializer for your test objects which points to valid data source.

```java
public class FooTest {
	@Mockcrumb(name = "foo1")
	private Foo foo1;
    
    @Before
    public void init() {
        FileBasedMockcrumbLoader loader = FileBasedMockcrumbLoader.of(
                Paths.get("/path/to/resources"),
                FullyQualifiedCrumbResolver.INSTANCE,
                JsonCrumbReader.INSTANCE);
        MockcrumbAnnotations.init(this, loader);
    }
    
    @Test
    public valueTest() {
    	assertThat(foo1.getValue()).isEqualTo("foo1Value");
    }
}
```

Data source defined under `/path/to/resources/Foo/foo1`:

```json
{
	"value": "foo1Value"
}
```

Please note that mock objects loader may be defined in the global scope and reused. Above example assumes usage of built-in file based loader for JSON formatted data.

## Distribution

The library is available under Maven Central repository. To use it simply add Mockcrumb as a dependency to your pom.xml file:

```xml
<dependency>
    <groupId>org.mockcrumb</groupId>
    <artifactId>mockcrumb-core</artifactId>
    <version>0.0.1</version>
</dependency>
```

Please visit Maven Central repository website for more details.
