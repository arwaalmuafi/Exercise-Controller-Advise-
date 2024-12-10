package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Gardener {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(columnDefinition = "varchar(10)")
        @NotEmpty(message = "Name cannot be null.")
        @Size(min = 3, message = "name should be more then 3")
        private String name;

        @Column(columnDefinition = "varchar(10)")
        @NotEmpty(message = "Phone number cannot be null.")
        private String phone;

        @Column(columnDefinition = "varchar(20)")
        @NotEmpty(message = "Specialization cannot be blank.")
        @Pattern(regexp = "^(Landscape|Irrigation|Pruning|Maintenance)$", message = "Specialization must be one of the following: Landscape, Irrigation, Pruning, Maintenance.")
        private String specialization;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public Gardener(){

        }
        public Gardener(Integer id, String name, String phone, String specialization) {
                this.id = id;
                this.name = name;
                this.phone = phone;
                this.specialization = specialization;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public @NotEmpty(message = "Name cannot be null.") @Size(min = 3, message = "name should be more then 3") String getName() {
                return name;
        }

        public void setName(@NotEmpty(message = "Name cannot be null.") @Size(min = 3, message = "name should be more then 3") String name) {
                this.name = name;
        }

        public @NotEmpty(message = "Phone number cannot be null.")  String getPhone() {
                return phone;
        }

        public void setPhone(@NotEmpty(message = "Phone number cannot be null.") @Pattern(regexp = "^[0-9]$", message = "Phone number must be valid and contain only digits.") String phone) {
                this.phone = phone;
        }

        public @NotEmpty(message = "Specialization cannot be blank.") @Pattern(regexp = "^(Landscape|Irrigation|Pruning|Maintenance)$", message = "Specialization must be one of the following: Landscape, Irrigation, Pruning, Maintenance.") String getSpecialization() {
                return specialization;
        }

        public void setSpecialization(@NotEmpty(message = "Specialization cannot be blank.") @Pattern(regexp = "^(Landscape|Irrigation|Pruning|Maintenance)$", message = "Specialization must be one of the following: Landscape, Irrigation, Pruning, Maintenance.") String specialization) {
                this.specialization = specialization;
        }
}
