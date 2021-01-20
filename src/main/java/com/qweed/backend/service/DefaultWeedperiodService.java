package com.qweed.backend.service;

import com.qweed.backend.jpa.Customer;
import com.qweed.backend.jpa.Smokesession;
import com.qweed.backend.jpa.Weedperiod;
import com.qweed.backend.jpa.WeedperiodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Service("weedperiodService")
public class DefaultWeedperiodService implements WeedperiodService {
    @Autowired
    WeedperiodRepository weedperiodRepository;

    @Override
    public void deleteByID(long id) {
        weedperiodRepository.deleteById(id);
    }

    @Override
    public Weedperiod findByID(Long id) {
        Optional<Weedperiod> weedperiod = weedperiodRepository.findWeedperiodById(id);
        if (weedperiod.isPresent()) {
            return calculateStats(weedperiod.get());
        }

        return weedperiod.orElse(null);
    }

    @Override
    public Weedperiod calculateStats(Weedperiod weedperiod) {
        weedperiod.setCostPerJoint(round(weedperiod.getCostPerGram() * weedperiod.getAverageGramPerJoint(), 2));
        if (weedperiod.getIsInitial()) {
            weedperiod.setAverageCostPerWeek(round(weedperiod.getCostPerJoint() * weedperiod.getAverageJointsSmokedPerWeek(), 2));
        } else {
            long longnum = 0;
            weedperiod.setTotalJoints(longnum);
            weedperiod.setTotalTime(longnum);

            if (!weedperiod.getSmokesessions().isEmpty()) {
                for (Smokesession smokesession : weedperiod.getSmokesessions()) {
                    weedperiod.setTotalJoints(weedperiod.getTotalJoints() + smokesession.getJointsSmoked());
                    weedperiod.setTotalTime(weedperiod.getTotalTime() + smokesession.getDuration());
                }
                weedperiod.setTotalCosts(round(weedperiod.getTotalJoints() * weedperiod.getCostPerJoint(), 2));
            }
            Calendar a = new GregorianCalendar();
            Calendar b = new GregorianCalendar();

            a.setTime(weedperiod.getStartDate());
            b.setTime(weedperiod.getEndDate());

            //int test =  b.get(Calendar.WEEK_OF_YEAR) - a.get(Calendar.WEEK_OF_YEAR);
            int Days = (int) (b.getTime().getTime() - a.getTime().getTime()) / (1000 * 60 * 60 * 24);
            double week = round((double) Days / 7, 2);
            double averageJointsSmokedPerWeek = (round(weedperiod.getTotalJoints() / week, 0));
            double averageDurationPerWeek = round(weedperiod.getTotalTime() / week, 0);
            double averageCostPerWeek = round(weedperiod.getTotalCosts() / week, 0);
            Weedperiod queried_weedperiod = findByCustomer(weedperiod.getCustomer());
            queried_weedperiod = calculateStats(queried_weedperiod);
            weedperiod.setAverageJointsSavedPerWeek(queried_weedperiod.getAverageJointsSmokedPerWeek() - averageJointsSmokedPerWeek);
            weedperiod.setAverageTimeSavedPerWeek(queried_weedperiod.getAverageDurationPerWeek() - averageDurationPerWeek);
            weedperiod.setAverageCostSavedPerWeek(queried_weedperiod.getAverageCostPerWeek() - averageCostPerWeek);
        }
        return weedperiod;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public Weedperiod findByCustomer(Customer customer) {
        Optional<Weedperiod> weedperiod = weedperiodRepository.findWeedperiodByCustomerAndIsInitial(customer, true);
        return weedperiod.orElse(null);
    }

    @Override
    public Weedperiod save(Weedperiod weedperiod) {
        if (weedperiod.getIsInitial()) {
            if (weedperiod.getName() == null || weedperiod.getCustomerName() == null || weedperiod.getCustomer() == null || weedperiod.getEndDate() == null || weedperiod.getStartDate() == null || weedperiod.getIsInitial() == null || weedperiod.getAverageDurationPerWeek() == null || weedperiod.getAverageGramPerJoint() == null || weedperiod.getAverageJointsSmokedPerWeek() == null || weedperiod.getCostPerGram() == null) {
                return null;
            }

            if (weedperiod.getStartDate().after(weedperiod.getEndDate())) {
                return null;
            }
            if (weedperiod.getEndDate().after(new Date())) {
                return null;
            }

            Weedperiod queried_weedperiod = findByCustomer(weedperiod.getCustomer());

            if (queried_weedperiod != null)
                return null;
        } else {
            Weedperiod queried_weedperiod = findByCustomer(weedperiod.getCustomer());
            if (weedperiod.getName() == null || weedperiod.getCustomer() == null || weedperiod.getStartDate() == null || weedperiod.getIsInitial() == null || weedperiod.getAverageGramPerJoint() == null || weedperiod.getCostPerGram() == null) {
                return null;
            }
            if (weedperiod.getStartDate().after(new Date())) {
                return null;
            }
            if (weedperiod.getStartDate().before(queried_weedperiod.getEndDate())) {
                return null;
            }
            /*if(weedperiod.getStartDate().after(queried_weedperiod.getEndDate())){
                return null;
            }*/
            if (weedperiod.getEndDate() == null) {
                weedperiod.setEndDate(weedperiod.getStartDate());
            }

            if (queried_weedperiod == null)
                return null;
        }

        return weedperiodRepository.save(weedperiod);
    }
}
