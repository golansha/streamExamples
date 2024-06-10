package streamsExamples;


public class Student
{
    private String id;
    private String name;
    private Address address;
    private String phone;
    private String department;
    private int year;
    private StudentStatus status;


    public Student(String str)
    {
        String[] strings=str.split(" ");
        setId(strings[1]);
        setName(strings[3]);
        setAddress(new Address(strings[5],strings[7]));
        setPhone(strings[9]);
        setDepartment( strings[11]);
        setYear(Integer.parseInt(strings[13]));
        setStatus(StudentStatus.valueOf(strings[15]));
    }
    public Student(String id, String name, Address address, String phone, String department, int year, StudentStatus status)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.department = department;
        this.year = year;
        this.status = status;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public StudentStatus getStatus()
    {
        return status;
    }

    public void setStatus(StudentStatus status)
    {
        this.status = status;
    }

    public String toString()
    {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", year=" + year +
                ", status=" + status +
                '}';
    }
  }
