package tw.com.shihyu.validation.jsr303.constraints;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lombok.Getter;

public class AssertThatTest {

  private Validator validator;

  @Before
  public void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @AssertThat(value = "this.age >= 18 || (this.age < 18  && not empty(this.parent))",
      propertyNode = "age")
  @Getter
  public class MyBean {
    int age;
    String parent;
  }

  @Test
  public void testPropertyNode() {
    MyBean bean = new MyBean();
    bean.age = 19;
    Set<ConstraintViolation<MyBean>> results = validator.validate(bean);
    Assert.assertTrue(results.isEmpty());

    bean.age = 15;
    results = validator.validate(bean);
    Assert.assertFalse(results.isEmpty());
    Assert.assertEquals(1, results.size());
    System.out.println(results);
    results.forEach(v -> Assert.assertEquals("age", v.getPropertyPath().toString()));
  }

  @AssertThat("this.age > 0 && this.birthday != null "
      + "&& forName('java.time.Period').between(this.birthday, forName('java.time.LocalDate').now()).getYears() == this.age")
  @Getter
  public static class MyBean2 {
    int age;
    LocalDate birthday;
  }


  @Test
  public void test() {
    MyBean2 bean = new MyBean2();
    bean.age = 5;
    bean.birthday = LocalDate.now().minusYears(bean.age);
    Set<ConstraintViolation<MyBean2>> results = validator.validate(bean);
    Assert.assertTrue(results.isEmpty());
  }

}
