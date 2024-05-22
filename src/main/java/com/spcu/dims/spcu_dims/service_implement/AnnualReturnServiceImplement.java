package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.controllers.CreatedDateSpecification;
import com.spcu.dims.spcu_dims.dto.AnnualReturnDTO;
import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.entities.User;
import com.spcu.dims.spcu_dims.entities.annual_return.*;
import com.spcu.dims.spcu_dims.repositories.*;
import com.spcu.dims.spcu_dims.services.AnnualReturnService;
import com.spcu.dims.spcu_dims.views.AnnualReturnView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnualReturnServiceImplement implements AnnualReturnService {

    private final AnnualReturnRepository annualReturnRepository;
    private final AnnualReturnViewRepository annualReturnViewRepository;
    private final ParishRepository parishRepository;
    private final BaptismRepository baptismRepository;
    private final BaptizedCatholicRepository baptizedCatholicRepository;
    private final CatechistRepository catechistRepository;
    private final CatholicActionRepository catholicActionRepository;
    private final DeathRepository deathRepository;
    private final EducationRepository educationRepository;
    private final InstitutionRepository institutionRepository;
    private final MarriageRepository marriageRepository;
    private final NonCatholicRepository nonCatholicRepository;
    private final PriestRepository priestRepository;
    private final PriestlyOrdinationRepository priestlyOrdinationRepository;
    private final ReligiousActivityRepository religiousActivityRepository;
    private final ReligiousLifeRepository religiousLifeRepository;
    private final SeminarianRepository seminarianRepository;
    private final UserRepository userRepository;

    String existsMessage = "Annual Return for Parish '%s' and Year '%d' already exists.";
    String doesNotExistsMessage = "Annual Return does not exist.";
    String parishDoesNotExistsMessage = "Parish with the name '%s' does not exist.";
    String userDoesNotExistsMessage = "User does not exist.";

    @Override
    public Object createAnnualReturn(AnnualReturnDTO annualReturnDTO, int id) {

        Optional<AnnualReturnView> optionalAnnualReturn = annualReturnViewRepository.findByParishAndYear(annualReturnDTO.getParish(), annualReturnDTO.getYear());
        if (optionalAnnualReturn.isPresent()) {
            return String.format(existsMessage, annualReturnDTO.getParish(), annualReturnDTO.getYear());
        }

        Optional<Parish> optionalParish = parishRepository.findByName(annualReturnDTO.getParish());
        if (optionalParish.isEmpty()) {
            return String.format(parishDoesNotExistsMessage, annualReturnDTO.getParish());
        }

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return userDoesNotExistsMessage;
        }

        Baptism baptism = Baptism
                .builder()
                .above8Year(annualReturnDTO.getAbove8Year())
                .upTo7Year(annualReturnDTO.getUpTo7Year())
                .build();
        baptismRepository.save(baptism);

        BaptizedCatholic baptizedCatholic = BaptizedCatholic
                .builder()
                .indigenous(annualReturnDTO.getIndigenous())
                .notIndigenous(annualReturnDTO.getNotIndigenous())
                .build();
        baptizedCatholicRepository.save(baptizedCatholic);

        Catechist catechist = Catechist
                .builder()
                .paid(annualReturnDTO.getPaid())
                .voluntary(annualReturnDTO.getVoluntary())
                .homeMissioner(annualReturnDTO.getHomeMissioner())
                .build();
        catechistRepository.save(catechist);

        CatholicAction catholicAction = CatholicAction
                .builder()
                .legionOfMary(annualReturnDTO.getLegionOfMary())
                .motherGuide(annualReturnDTO.getMotherGuide())
                .stVincentDePaul(annualReturnDTO.getStVincentDePaul())
                .parishElder(annualReturnDTO.getParishElder())
                .youthGroup(annualReturnDTO.getYouthGroup())
                .other(annualReturnDTO.getCaOther())
                .build();
        catholicActionRepository.save(catholicAction);

        Death death = Death
                .builder()
                .catholic(annualReturnDTO.getCatholic())
                .religious(annualReturnDTO.getReligious())
                .priest(annualReturnDTO.getPriest())
                .build();
        deathRepository.save(death);

        Education education = Education
                .builder()
                .basic(annualReturnDTO.getBasic())
                .middle(annualReturnDTO.getMiddle())
                .high(annualReturnDTO.getHigh())
                .university(annualReturnDTO.getUniversity())
                .build();
        educationRepository.save(education);

        Institute institute = Institute
                .builder()
                .poor(annualReturnDTO.getPoor())
                .boardingForPoor(annualReturnDTO.getBoardingForPoor())
                .catechist(annualReturnDTO.getCatechist())
                .orphan(annualReturnDTO.getOrphan())
                .seminary(annualReturnDTO.getSeminary())
                .novitiate(annualReturnDTO.getNovitiate())
                .trainingForHomeMissioner(annualReturnDTO.getTrainingForHomeMissioner())
                .build();
        institutionRepository.save(institute);

        Marriage marriage = Marriage
                .builder()
                .betweenCatholic(annualReturnDTO.getBetweenCatholic())
                .withNonChristian(annualReturnDTO.getWithNonChristian())
                .withNonCatholicChristian(annualReturnDTO.getWithNonCatholicChristian())
                .build();
        marriageRepository.save(marriage);

        NonCatholic nonCatholic = NonCatholic
                .builder()
                .nonCatholicChristian(annualReturnDTO.getNonCatholicChristian())
                .nonChristian(annualReturnDTO.getNonChristian())
                .build();
        nonCatholicRepository.save(nonCatholic);

        Priest priest = Priest
                .builder()
                .female(annualReturnDTO.getFemalePriest())
                .male(annualReturnDTO.getMalePriest())
                .build();
        priestRepository.save(priest);

        PriestlyOrdination priestlyOrdination = PriestlyOrdination
                .builder()
                .religiousProfession(annualReturnDTO.getReligiousProfession())
                .graduated(annualReturnDTO.getGraduated())
                .build();
        priestlyOrdinationRepository.save(priestlyOrdination);

        ReligiousActivity religiousActivity = ReligiousActivity
                .builder()
                .seminar(annualReturnDTO.getSeminar())
                .recollections(annualReturnDTO.getRecollections())
                .retreats(annualReturnDTO.getRetreats())
                .other(annualReturnDTO.getOther())
                .build();
        religiousActivityRepository.save(religiousActivity);

        ReligiousLife religiousLife = ReligiousLife
                .builder()
                .male(annualReturnDTO.getRlMale())
                .female(annualReturnDTO.getRlFemale())
                .build();
        religiousLifeRepository.save(religiousLife);

        Seminarian seminarian = Seminarian
                .builder()
                .major(annualReturnDTO.getMajor())
                .minor(annualReturnDTO.getMinor())
                .build();
        seminarianRepository.save(seminarian);

        AnnualReturn annualReturn = AnnualReturn
                .builder()
                .year(annualReturnDTO.getYear())
                .totalActualResident(annualReturnDTO.getTotalActualResident())
                .catechumen(annualReturnDTO.getCatechumen())
                .chapel(annualReturnDTO.getChapel())
                .visitedPlace(annualReturnDTO.getVisitedPlace())
                .project(annualReturnDTO.getProject())
                .matrimonialCases(annualReturnDTO.getMatrimonialCases())
                .parish(optionalParish.get())
                .baptism(baptism)
                .baptizedCatholic(baptizedCatholic)
                .createdUser(user.get())
                .catechist(catechist)
                .catholicAction(catholicAction)
                .death(death)
                .education(education)
                .institute(institute)
                .priest(priest)
                .marriage(marriage)
                .nonCatholic(nonCatholic)
                .religiousLife(religiousLife)
                .priestlyOrdination(priestlyOrdination)
                .religiousActivity(religiousActivity)
                .seminarian(seminarian)
                .build();

        return annualReturnRepository.save(annualReturn);
    }


    @Override
    public AnnualReturn findById(int id) {
        return null;
    }

    @Override
    public AnnualReturn delete(int id) {
        Optional<AnnualReturn> annualReturn = annualReturnRepository.findById(id);
        if (annualReturn.isPresent()) {
            annualReturn.get().setDelete(true);
            return annualReturnRepository.save(annualReturn.get());
        }
        return null;
    }

    @Override
    public Object update(AnnualReturnDTO annualReturnDTO) {

        Optional<Parish> optionalParish = parishRepository.findByName(annualReturnDTO.getParish());
        if (optionalParish.isEmpty()) {
            return String.format(parishDoesNotExistsMessage, annualReturnDTO.getParish());
        }

        Optional<AnnualReturn> annualReturnExist = annualReturnRepository.findByParishAndYearAndIdNot(optionalParish.get(), annualReturnDTO.getYear(), annualReturnDTO.getId());
        if (annualReturnExist.isPresent()) {
            return String.format(existsMessage, annualReturnDTO.getParish(), annualReturnDTO.getYear());
        }

        Optional<AnnualReturn> optionalAnnualReturn= annualReturnRepository.findById(annualReturnDTO.getId());
        if (optionalAnnualReturn.isEmpty()){
            return String.format(doesNotExistsMessage);
        }

        Optional<Baptism> baptism = baptismRepository.findById(optionalAnnualReturn.get().getBaptism().getId());
        baptism.ifPresent(b -> {
            b.setAbove8Year(annualReturnDTO.getAbove8Year());
            b.setUpTo7Year(annualReturnDTO.getUpTo7Year());
            baptismRepository.save(b);
        });

        Optional<BaptizedCatholic> baptizedCatholic = baptizedCatholicRepository.findById(optionalAnnualReturn.get().getBaptizedCatholic().getId());
        baptizedCatholic.ifPresent(b -> {
            b.setIndigenous(annualReturnDTO.getIndigenous());
            b.setNotIndigenous(annualReturnDTO.getNotIndigenous());
            baptizedCatholicRepository.save(b);
        });

        Optional<Catechist> catechist = catechistRepository.findById(optionalAnnualReturn.get().getCatechist().getId());
        catechist.ifPresent(c -> {
            c.setPaid(annualReturnDTO.getPaid());
            c.setVoluntary(annualReturnDTO.getVoluntary());
            c.setHomeMissioner(annualReturnDTO.getHomeMissioner());
            catechistRepository.save(c);
        });

        Optional<CatholicAction> catholicAction = catholicActionRepository.findById(optionalAnnualReturn.get().getCatholicAction().getId());
        catholicAction.ifPresent(c -> {
            c.setLegionOfMary(annualReturnDTO.getLegionOfMary());
            c.setMotherGuide(annualReturnDTO.getMotherGuide());
            c.setStVincentDePaul(annualReturnDTO.getStVincentDePaul());
            c.setParishElder(annualReturnDTO.getParishElder());
            c.setYouthGroup(annualReturnDTO.getYouthGroup());
            c.setOther(annualReturnDTO.getCaOther());

            catholicActionRepository.save(c);

        });

        Optional<Death> death = deathRepository.findById(optionalAnnualReturn.get().getDeath().getId());
        death.ifPresent(d -> {
            d.setCatholic(annualReturnDTO.getCatholic());
            d.setReligious(annualReturnDTO.getReligious());
            d.setPriest(annualReturnDTO.getPriest());

            deathRepository.save(d);
        });

        Optional<Education> education = educationRepository.findById(optionalAnnualReturn.get().getEducation().getId());
        education.ifPresent(e -> {
            e.setBasic(annualReturnDTO.getBasic());
            e.setMiddle(annualReturnDTO.getMiddle());
            e.setHigh(annualReturnDTO.getHigh());
            e.setUniversity(annualReturnDTO.getUniversity());
            educationRepository.save(e);
        });

        Optional<Institute> institute = institutionRepository.findById(optionalAnnualReturn.get().getId());
        institute.ifPresent(i -> {
            i.setPoor(annualReturnDTO.getPoor());
            i.setBoardingForPoor(annualReturnDTO.getBoardingForPoor());
            i.setCatechist(annualReturnDTO.getCatechist());
            i.setOrphan(annualReturnDTO.getOrphan());
            i.setSeminary(annualReturnDTO.getSeminary());
            i.setNovitiate(annualReturnDTO.getNovitiate());
            i.setTrainingForHomeMissioner(annualReturnDTO.getTrainingForHomeMissioner());

            institutionRepository.save(i);
        });

        Optional<Marriage> marriage = marriageRepository.findById(optionalAnnualReturn.get().getMarriage().getId());
        marriage.ifPresent(m -> {
            m.setBetweenCatholic(annualReturnDTO.getBetweenCatholic());
            m.setWithNonChristian(annualReturnDTO.getWithNonChristian());
            m.setWithNonCatholicChristian(annualReturnDTO.getWithNonCatholicChristian());

            marriageRepository.save(m);
        });

        Optional<NonCatholic> nonCatholic = nonCatholicRepository.findById(optionalAnnualReturn.get().getNonCatholic().getId());
        nonCatholic.ifPresent(n -> {
            n.setNonCatholicChristian(annualReturnDTO.getNonCatholicChristian());
            n.setNonChristian(annualReturnDTO.getNonChristian());

            nonCatholicRepository.save(n);
        });


        Optional<Priest> priest = priestRepository.findById(optionalAnnualReturn.get().getPriest().getId());
        priest.ifPresent(o -> {
            o.setFemale(annualReturnDTO.getFemalePriest());
            o.setMale(annualReturnDTO.getMalePriest());
            priestRepository.save(o);
        });

        Optional<PriestlyOrdination> priestlyOrdination = priestlyOrdinationRepository.findById(optionalAnnualReturn.get().getPriest().getId());
        priestlyOrdination.ifPresent(po -> {
            po.setReligiousProfession(annualReturnDTO.getReligiousProfession());
            po.setGraduated(annualReturnDTO.getGraduated());
            priestlyOrdinationRepository.save(po);
        });

        Optional<ReligiousActivity> religiousActivity = religiousActivityRepository.findById(optionalAnnualReturn.get().getReligiousActivity().getId());
        religiousActivity.ifPresent(ra -> {
            ra.setSeminar(annualReturnDTO.getSeminar());
            ra.setRecollections(annualReturnDTO.getRecollections());
            ra.setRetreats(annualReturnDTO.getRetreats());
            ra.setOther(annualReturnDTO.getOther());
            religiousActivityRepository.save(ra);
        });

        Optional<ReligiousLife> religiousLife = religiousLifeRepository.findById(optionalAnnualReturn.get().getReligiousLife().getId());
        religiousLife.ifPresent(rl -> {
            rl.setMale(annualReturnDTO.getRlMale());
            rl.setFemale(annualReturnDTO.getRlFemale());
            religiousLifeRepository.save(rl);
        });

        Optional<Seminarian> seminarian = seminarianRepository.findById(optionalAnnualReturn.get().getSeminarian().getId());
        seminarian.ifPresent(s -> {
            s.setMajor(annualReturnDTO.getMajor());
            s.setMinor(annualReturnDTO.getMinor());
            seminarianRepository.save(s);
        });



        optionalAnnualReturn.get().setYear(annualReturnDTO.getYear());
        optionalAnnualReturn.get().setTotalActualResident(annualReturnDTO.getTotalActualResident());
        optionalAnnualReturn.get().setCatechumen(annualReturnDTO.getCatechumen());
        optionalAnnualReturn.get().setChapel(annualReturnDTO.getChapel());
        optionalAnnualReturn.get().setVisitedPlace(annualReturnDTO.getVisitedPlace());
        optionalAnnualReturn.get().setProject(annualReturnDTO.getProject());
        optionalAnnualReturn.get().setMatrimonialCases(annualReturnDTO.getMatrimonialCases());
        optionalAnnualReturn.get().setParish(optionalParish.get());
        optionalAnnualReturn.get().setBaptism(baptism.orElse(null));
        optionalAnnualReturn.get().setBaptizedCatholic(baptizedCatholic.orElse(null));
        optionalAnnualReturn.get().setCatechist(catechist.orElse(null));
        optionalAnnualReturn.get().setCatholicAction(catholicAction.orElse(null));
        optionalAnnualReturn.get().setDeath(death.orElse(null));
        optionalAnnualReturn.get().setEducation(education.orElse(null));
        optionalAnnualReturn.get().setInstitute(institute.orElse(null));
        optionalAnnualReturn.get().setPriest(priest.orElse(null));
        optionalAnnualReturn.get().setMarriage(marriage.orElse(null));
        optionalAnnualReturn.get().setNonCatholic(nonCatholic.orElse(null));
        optionalAnnualReturn.get().setReligiousLife(religiousLife.orElse(null));
        optionalAnnualReturn.get().setPriestlyOrdination(priestlyOrdination.orElse(null));
        optionalAnnualReturn.get().setReligiousActivity(religiousActivity.orElse(null));
        optionalAnnualReturn.get().setSeminarian(seminarian.orElse(null));


        return annualReturnRepository.save(optionalAnnualReturn.get());
    }

    @Override
    public DataTablesOutput<AnnualReturnView> annualReturnTable(DataTablesInput input, CreatedDateSpecification<AnnualReturnView> createdDateSpecification) {
        return annualReturnViewRepository.findAll(input, createdDateSpecification);
    }


}
