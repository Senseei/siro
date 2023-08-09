package com.internacao.siro.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.internacao.siro.dto.employee.NewEmployeeDTO;
import com.internacao.siro.dto.employee.UpdateEmployeeDTO;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Person {
    
    private Long re;

    public Employee() {}

	public Employee(String name, Long re) {
        super(name);
        setRe(re);
    }

    public Employee(String name, LocalDate birthday, Long re) {
        super(name, birthday);
        setRe(re);
    }

	public Employee(String name, LocalDate birthday, String cpf, Long re) {
        super(name, birthday, cpf);
        setRe(re);
    }

    public Employee(NewEmployeeDTO body) {
        super(body.getName(), body.getBirthday(), body.getCpf());
        re = body.getRe();
    }

    public Long getRe() {
        return re;
    }

    public void setRe(Long re) {
        this.re = re;
    }

    public void updateEmployee(UpdateEmployeeDTO body) {
        if (body.getName() != null)
            setName(body.getName());
        if (body.getBirthday() != null)
            setBirthday(body.getBirthday());
        if (body.getCpf() != null)
            setCpf(body.getCpf());
        if (body.getRe() != null)
            re = body.getRe();
    }

    public void reverseDelete(NewEmployeeDTO body) {
        setName(body.getName());
        setBirthday(body.getBirthday());
        setCpf(body.getCpf());
        re = body.getRe();
        setDeletedAt(null);
    }

    @Override
	public int hashCode() {
		return Objects.hash(re);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(re, other.re);
	}
}