document.addEventListener("DOMContentLoaded", async function () {
    await fetch('/spcu-dims/api/v1/activity/activity&parish')
        .then(async response => {
            if (response.ok) {
                return response.json(); // Return the parsed JSON data
            } else {
                throw new Error('Network response was not ok.');
            }
        })
        .then(data => {
            // Assuming data.activityCount contains the value you want to set
            let activityCounter = document.getElementsByClassName('activityCounter');
            let parishCounter = document.getElementsByClassName('parishCounter');

            for (let i = 0; i < activityCounter.length; i++) {
                activityCounter[i].classList.add('purecounter');
            }

            for (let i = 0; i < parishCounter.length; i++) {
                parishCounter[i].classList.add('purecounter');
            }


            new PureCounter({
                selector: '.activityCounter',
                start: 0,
                end: data.activity,
                duration: 1
            })

            new PureCounter({
                selector: '.parishCounter',
                start: 0,
                end: data.parish,
                duration: 1
            })

        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });

})

// Function to add the HTML content to the modal body
function addContentToModal(parameter) {
    console.log(parameter)
    let htmlContent = ''

    switch (parameter) {
        case 'Education' :
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To ensure that safe and high-quality education remains accessible during the ongoing crisis in Myanmar and to support children not currently attending school to enter or return to quality learning opportunities.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           Improve access to essential educational assistance for vulnerable children in Myanmar.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To advocate strongly, cooperate actively, and collaborate effectively with all education partners, including internal church members and external organizations, to establish a highly productive and mutually beneficial relationship.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To effectively improve the skills of teachers to deliver quality education programs.
                        </span>
                    </p>
                `;
            break;

        case 'Health' :
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Enhance primary healthcare access by delivering essential routine basic healthcare services, identifying and managing emergency cases, and playing a crucial role in disease surveillance. Through our comprehensive health services, we aim to witness the loving care and healing ministry of Jesus Christ.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           Ensure that essential healthcare services are continuously accessible during emergencies, especially for women, children, and individuals with chronic health conditions to effectively address the healthcare needs of those who require it the most.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Enhance the skills of primary healthcare workers and improve the quality of healthcare at Catholic health facilities.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To advocate strongly, cooperate actively, and collaborate effectively with all health partners, including internal Church members and external organizations, to establish a highly productive and mutually beneficial relationship.
                        </span>
                    </p>
                `;
            break;

        case 'Migration' :
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Ensure that migrants and their families receive protection, assistance, and empowerment to address their vulnerabilities and achieve sustainable reintegration.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           Enhance the abilities and security of individuals who have migrated from conflict zones in Myanmar to provide them with improved employment prospects and working conditions.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To promote cooperation among potential employers, sector associations, and skill development partners to enable migrant workers to access safe migration and fair employment opportunities.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Establish effective communication and coordination methods for origin and return communities to prevent unsafe and irregular migration and promote sustainable reintegration.
                        </span>
                    </p>
                `;
            break;

        case 'Humanitarian' :
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To ensure the well-being of those affected by the conflict in Myanmar while also maintaining their human dignity. We aim to achieve durable solutions that meet international standards whenever possible and to enhance respect for the rights of those who have been affected.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           To advocate strongly, cooperate actively, and collaborate effectively with all humanitarian actors, including internal church members and external organizations, to improve the effectiveness of the humanitarian response to the Myanmar crisis.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Develop innovative and diverse fundraising options for the Diocesan network to be able to fulfill resource requirements for the humanitarian response plan of the Church.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To enhance the skills and abilities of people involved in humanitarian aid work in CBCM and Diocesan organizations.
                        </span>
                    </p>
                `;
            break;

        case 'Safeguarding' :
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            The Bishops Conference of Myanmar develops and implements safeguarding standards to ensure that every Church entity, commission, and congregation across Myanmar places the safety of children and adults at the forefront of their planning, thinking, and actions.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           The Bishops Conference of Myanmar will enhance the safeguarding practices across various levels of the Church by developing and strengthening its systems, mechanisms, and consistency in these practices.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            The Bishops Conference of Myanmar assists dioceses in developing and implementing CBCM Safeguarding Policy Standards and Procedures.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            The Bishops Conference of Myanmar establishes a safeguarding structure responsible for ensuring accountability and providing guidance on policy implementation and best practices.
                        </span>
                    </p>
                `;
            break;

        case 'Faith Formation':
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            The Church will assist individual of all ages in developing and achieving their Christian maturity as human beings.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           To provide guidance and support to individual faithful of all ages who seek to deepen their understanding of the essential aspects of the Catholic faith and its traditions.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To assist those impacted by the Myanmar crisis to improve their mental, emotional, social, and spiritual well-being.
                        </span>
                    </p>
                `;
            break;

        case 'Enhancing Capacities & Competencies' :
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           To promote servant leadership in the Church that prioritizes service, promotes fraternal collaboration and partnership, values honesty, and maintains a sense of responsibility and accountability.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To improve the abilities of Church Commissions, Associations, and Pastoral Councils to effectively address the needs of those impacted by the conflict in Myanmar.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Improve the capacity of Church Commissions, Associations, and Pastoral Councils to effectively utilize innovative fundraising methods and ensure equitable and impartial allocation of funds across the entire Church hierarchy.
                        </span>
                    </p>
                `;
            break;

        case 'Social-Pastoral Coordination':
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            aAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           Encourage and foster collaboration between Catholic dioceses, parishes, religious congregations, and lay organizations.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Develop and maintain the Terms of Reference (TOR) for the Catholic Church in Myanmar.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Improving governance policies and processes to enhance collaboration, and communication, strengthen coordination, and ensure efficient functioning among various religious organizations and commissions (CRCM) in Myanmar's Bishops Conference and Conference of Religious.
                        </span>
                    </p>
                `;
            break;

        case 'Inter-Religious Engagement and Ecumenical Engagement':
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Interreligious and ecumenical dialogue is a simple concept: persons of different faiths meet to have a conversation. It is a conversation among people of different faiths on a common subject, the primary purpose of which is for each participant to learn from the other to change and grow. It is not a debate. Through interreligious and ecumenical engagement, the ‘Elite Leadership’ of high-level religious leaders for collective advocacy and joint action for peace is strengthened. Interfaith dialogue can strengthen the ‘Elite Interfaith body’ for mediation between conflicting parties. Interfaith dialogue can bring people from different religions to nurture agents of reconciliation.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           To promote peaceful conflict resolution among communities living in conflict-affected areas by offering secure platforms that facilitate better communication among community members.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To create initiatives at the local level that promote social cohesion and bring together diverse groups in a community.
                        </span>
                    </p>

                `;
            break;

        case 'Environmental Justice' :
            htmlContent = `
                    <h2>&#8226; Goal</h2>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Laudate Deum: While the previous Nation Building strategic plan referred to Laudato Si encyclical, This Synodal Journey strategic plan followed the apostolic exhortation Laudate Deum in which the Holy Father pointed out the imminent global climate crisis, unchecked technocratic growth undermining the human responsibilities, weaken international politics, the importance of climate conference COP and end up His document with spiritual motivation. He urges for more durable actions so that our common home is saved. Following this, the Synodal journey strategic plan emphasized Environmental justice as one of the 4 internal strategic directions.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 1</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                           The Catholic Bishops' Conference of Myanmar will be actively involved in the Laudato Si movement, taking part in its initiatives and working to strengthen its efforts.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 2</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            Implement an environmental justice education program within the Catholic Church of Myanmar with the assistance of the Laudato Si movement.
                        </span>
                    </p>
                    <hr>
                    <h4>&#8226; Objective 3</h4>
                    <p class="mt-4 mb-4">
                        <span style="margin-left: 40px;">
                            To develop and implement an advocacy system that can gather the backing of church leaders and collaborate with other organisations with similar goals.
                        </span>
                    </p>
                `;
            break;

        default:
            htmlContent = 'Invalid parameter';
    }


    // Define the HTML content


    // Get the modal body element
    let modalBody = document.querySelector('.modal-body');

    // Add the HTML content to the modal body
    modalBody.innerHTML = htmlContent;
}

// Function to open the modal and set content based on parameter
function openModal(parameter) {
    // Add content to modal based on parameter
    addContentToModal(parameter);

    // Show the modal
    var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
    myModal.show();
}
