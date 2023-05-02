package com.example.demo;

import com.example.demo.data.CategoriesData;
import com.example.demo.data.LeadData;
import com.example.demo.repos.Categories;
import com.example.demo.repos.Leads;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.stream.Stream;

@Route("")
public class MainView extends Div {

    final Categories repoCategories;
    final Leads repoLeads;
    private TextArea descriptionField;
    private TextField name;
    private TextField address;
    private TextField phoneNumber;
    private TextField email;

    public MainView(Categories repoCategories, Leads repoLeads) {
        this.repoCategories = repoCategories;
        this.repoLeads = repoLeads;

        VerticalLayout leftSideLayout = rightSideLayout();
        VerticalLayout rightSideLayout = leftSideLayout();

        HorizontalLayout serviceLayout = new HorizontalLayout(rightSideLayout, leftSideLayout);

        add(header(), serviceLayout);

    }

    private VerticalLayout rightSideLayout() {
        Select<String> select = new Select<>();
        select.setLabel("Select category");
        select.setItems(repoCategories.findAll().stream().map(CategoriesData::getName).toList());
        select.setValue(repoCategories.findAll().stream().findFirst().get().getName());


        this.descriptionField = new TextArea("enter description here");
        descriptionField.setId("description");
        descriptionField.setHeight("200px");
        descriptionField.setHelperText("input description here");

        Button addButton = new Button("Send my contact information");
        addButton.addClickListener(click -> {
            Stream<CategoriesData> categoriesDataStream = repoCategories.findAll().stream().filter(c -> c.getName().equals(select.getValue()));
            CategoriesData category = categoriesDataStream.findFirst().orElse(null);
            LeadData leadData = new LeadData()
                    .setName(name.getValue())
                    .setAddress(address.getValue())
                    .setPhone(phoneNumber.getValue())
                    .setEmail(email.getValue())
                    .setDescription(this.descriptionField.getValue())
                    .setCategory(category);
            try {
                repoLeads.save(leadData);
                System.out.println("new lead " + leadData.getName() + " saved");
                name.setValue("");
                address.setValue("");
                phoneNumber.setValue("");
                email.setValue("");
                descriptionField.setValue("");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        addButton.addClickShortcut(Key.ENTER);

        VerticalLayout serviceLayout = new VerticalLayout(
                select,
                descriptionField,
                addButton);
        serviceLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        return serviceLayout;
    }

    private VerticalLayout leftSideLayout() {

        name = new TextField("Enter Name");
        address = new TextField("Enter address");
        phoneNumber = new TextField("Enter phone number");
        email = new TextField("Enter email");

        VerticalLayout layout = new VerticalLayout(
                name,
                address,
                phoneNumber,
                email);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        return layout;
    }

    private static VerticalLayout header() {
        VerticalLayout h = new VerticalLayout(new H1("Select a service category from the list and give a short description"));
        h.setAlignItems(FlexComponent.Alignment.CENTER);
        return h;
    }
}
