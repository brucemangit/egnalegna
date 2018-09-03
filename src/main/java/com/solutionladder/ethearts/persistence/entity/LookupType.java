package com.solutionladder.ethearts.persistence.entity;

import javax.persistence.Entity;

/**
 * Model representing lookup type table;
 * The lookup table will contain basically a simple key value pair along with what type of look up it is.
 * For any kind of table that is simple key value pair like category, role.. this class will contain what 
 * the key value is for.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Entity
public class LookupType extends Lookup{
}
