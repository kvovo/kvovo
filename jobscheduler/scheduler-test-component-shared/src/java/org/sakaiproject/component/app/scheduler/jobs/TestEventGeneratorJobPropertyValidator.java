package org.sakaiproject.component.app.scheduler.jobs;

import org.sakaiproject.api.app.scheduler.ConfigurableJobPropertyValidationException;
import org.sakaiproject.api.app.scheduler.ConfigurableJobPropertyValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: duffy
 * Date: Nov 2, 2010
 * Time: 9:57:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestEventGeneratorJobPropertyValidator implements ConfigurableJobPropertyValidator
{

    public void assertValid(String propertyLabel, String value)
        throws ConfigurableJobPropertyValidationException
    {
        if (propertyLabel.equalsIgnoreCase(TestEventGeneratorJob.MININTERVAL_PROPERTY) ||
            propertyLabel.equalsIgnoreCase(TestEventGeneratorJob.NUMEVENTS_PROPERTY))
        {
            int
                temp = 0;
            boolean
                success = true;

            try
            {
                temp = Integer.parseInt(value);
            }
            catch (NumberFormatException nfe)
            {
                success = false;
            }

            if (!success || temp < 1)
            {
                ConfigurableJobPropertyValidationException
                    cjpve = null;

                if (propertyLabel.equalsIgnoreCase(TestEventGeneratorJob.MININTERVAL_PROPERTY))
                {
                    cjpve = new ConfigurableJobPropertyValidationException("error.minInterval.format");
                }
                else
                {
                    cjpve = new ConfigurableJobPropertyValidationException("error.numEvents.format");
                }

                throw cjpve;
            }
        }
        else if (propertyLabel.equalsIgnoreCase(TestEventGeneratorJob.STARTDATE_PROPERTY))
        {
            SimpleDateFormat
                sdf = new SimpleDateFormat("yyyy-MM-dd");

            try
            {
                sdf.parse(value);
            }
            catch (ParseException e)
            {
                throw new ConfigurableJobPropertyValidationException ("error.startDate.format");
            }
        }
    }
}
