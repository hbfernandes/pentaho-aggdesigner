/*
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU General Public License, version 2 as published by the Free Software
* Foundation.
*
* You should have received a copy of the GNU General Public License along with this
* program; if not, you can obtain a copy at http://www.gnu.org/licenses/gpl-2.0.html
* or from the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* See the GNU General Public License for more details.
*
*
* Copyright 2006 - 2016 Pentaho Corporation.  All rights reserved.
*/

package org.pentaho.aggdes.model.mondrian.validate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mondrian.olap.MondrianDef.Cube;
import mondrian.olap.MondrianDef.Schema;

import org.pentaho.aggdes.model.ValidationMessage;
import org.pentaho.aggdes.model.mondrian.validate.MondrianSchemaValidator;

/**
 * Delegates to a list of <code>MondrianSchemaValidator</code>s.
 * 
 * @author mlowery
 */
public class MondrianSchemaValidatorManager implements MondrianSchemaValidator {

  List<MondrianSchemaValidator> validators = Collections.emptyList();

  public List<ValidationMessage> validateCube(Schema schema, Cube cube, Connection conn) {
    List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
    for (MondrianSchemaValidator validator : validators) {
      messages.addAll(validator.validateCube(schema, cube, conn));
    }
    return messages;
  }

  public void setValidators(List<MondrianSchemaValidator> validators) {
    this.validators = validators;
  }

}
