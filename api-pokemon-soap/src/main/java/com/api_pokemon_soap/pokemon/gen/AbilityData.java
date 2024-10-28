//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.27 a las 02:03:21 AM CST 
//


package com.api_pokemon_soap.pokemon.gen;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AbilityData complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AbilityData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ability" type="{http://www.api-pokemon-soap.com/pokemon/gen}Ability"/&gt;
 *         &lt;element name="is_hidden" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="slot" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbilityData", propOrder = {
    "ability",
    "isHidden",
    "slot"
})
public class AbilityData {

    @XmlElement(required = true)
    protected Ability ability;
    @XmlElement(name = "is_hidden")
    protected boolean isHidden;
    protected int slot;

    /**
     * Obtiene el valor de la propiedad ability.
     * 
     * @return
     *     possible object is
     *     {@link Ability }
     *     
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * Define el valor de la propiedad ability.
     * 
     * @param value
     *     allowed object is
     *     {@link Ability }
     *     
     */
    public void setAbility(Ability value) {
        this.ability = value;
    }

    /**
     * Obtiene el valor de la propiedad isHidden.
     * 
     */
    public boolean isIsHidden() {
        return isHidden;
    }

    /**
     * Define el valor de la propiedad isHidden.
     * 
     */
    public void setIsHidden(boolean value) {
        this.isHidden = value;
    }

    /**
     * Obtiene el valor de la propiedad slot.
     * 
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Define el valor de la propiedad slot.
     * 
     */
    public void setSlot(int value) {
        this.slot = value;
    }

}
