package org.lsi.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VR")

public class Versment extends Operation{

}
